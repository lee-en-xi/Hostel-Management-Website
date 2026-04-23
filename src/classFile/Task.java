package classFile;

import interfaceFile.IDHandler;

import java.text.ParseException;
import java.util.Calendar;               // Import the Calendar class for working with dates and times.
import java.text.SimpleDateFormat;       // Import the SimpleDateFormat class for formatting dates.
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;                   // Import the Date class for working with dates.
import java.util.List;
import java.util.Random;                 // Import the Random class for generating random values.
import java.util.concurrent.TimeUnit;    // Import the TimeUnit class for working with time units.

public final class Task implements IDHandler {

    private final FileHandlerImp fileHandlerImp;
    private String taskID;
    private String orderID;
    private String pickUpAddress;
    private String destinationAddress;
    private String customerName;
    private String customerID;
    private String shopName;
    private String deliveryFee;
    private String requestedPickUpTime;
    private String requestedDeliveryTime;

    public Task() {
        fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
    }

    public Task(String orderID, String pickUpAddress, String shopName, String destinationAddress, String customerName, String customerID) {
        fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        this.taskID = generateID();
        this.orderID = orderID;
        this.pickUpAddress = pickUpAddress;
        this.shopName = shopName;
        this.destinationAddress = destinationAddress;
        this.customerName = customerName;
        this.customerID = customerID;
        this.requestedPickUpTime = generateTime(10, 20);   //Random Current Time + 10 to 30 Minutes
        this.requestedDeliveryTime = generateTime(40, 10); //Random Current Time + 40 to 50 Minutes
        this.deliveryFee = generateDeliveryFee(requestedPickUpTime, requestedDeliveryTime);
    }

    public Task(String orderID, String pickUpAddress, String shopName, String destinationAddress, String customerName, String customerID, String requestedPickUpTime, String requestedDeliveryTime, String deliveryFee) {
        fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        this.taskID = generateID();
        this.orderID = orderID;
        this.pickUpAddress = pickUpAddress;
        this.shopName = shopName;
        this.destinationAddress = destinationAddress;
        this.customerName = customerName;
        this.customerID = customerID;
        this.requestedPickUpTime = requestedPickUpTime;   //Random Current Time + 10 to 30 Minutes
        this.requestedDeliveryTime = requestedDeliveryTime; //Random Current Time + 40 to 50 Minutes
        this.deliveryFee = deliveryFee;
    }

    public String getTaskID() {
        return this.taskID;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public static String generateTime(int rangeMinMinutes, int range) {
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Generate a random time interval
        Random random = new Random();
        int randomHours = random.nextInt(range) + rangeMinMinutes;
        double randomMinutes = random.nextDouble();
        long randomTimeMillis = TimeUnit.MINUTES.toMillis((long) (randomHours + randomMinutes));

        // Calculate the new date and time
        Date randomDateTime = new Date(currentDate.getTime() + randomTimeMillis);

        // Format the date and time as a string in the desired format (yyyy-MM-dd HH:mm)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateFormat.format(randomDateTime);

        return formattedDateTime;
    }

    @Override
    public String generateID() {
        String filePath = "src/textFile/task.txt";

        boolean isDuplicate;
        int numID;
        String ID;

        // Read Whole File Data
        List<String[]> datalist = fileHandlerImp.readWholeData(filePath);

        // Get the ID of the last rows of Data and extract the only Number
        if (datalist.toString().equals("[]")) {
            ID = "T0001";
        } else {
            ID = datalist.get(datalist.size() - 1)[0].replace("T", "");
            numID = Integer.parseInt(ID);

            do {
                // Increase the ID index
                numID = numID + 1;

                // Compensate for vacancies
                String vac = "";
                if (numID < 1000 && numID >= 100) {
                    vac = "0";
                } else if (numID < 100 && numID >= 10) {
                    vac = "00";
                } else if (numID < 10) {
                    vac = "000";
                }

                // Combine all to String
                Integer.toString(numID);
                ID = "T" + vac + numID;

                // Check Whether if Duplicate in Data[][]
                isDuplicate = false;
                for (String[] lines : datalist) {
                    if (lines[0].equals(ID)) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);
        }
        return ID;
    }

    public static String generateDeliveryFee(String pickUpTime, String deliveryTime) {
        //Define the date format for parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //Parse the time string into a LocalDateTime object
        LocalDateTime startTime = LocalDateTime.parse(pickUpTime, formatter);
        LocalDateTime endTime = LocalDateTime.parse(deliveryTime, formatter);

        //Calculate the Duration between the two times
        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutes();

        //Calculate deliveryFee depends on Duration
        String fee;

        if (minutes >= 10 && minutes <= 19) {
            fee = "6.00";
        } else if (minutes >= 20 && minutes <= 29) {
            fee = "8.00";
        } else if (minutes >= 30 && minutes <= 39) {
            fee = "10.00";
        } else if (minutes >= 40 && minutes <= 50) {
            fee = "12.00";
        } else {
            fee = "4.00";
        }

        return fee;
    }

    public static String formatTime(String inputDateTime) {
        try {
            // Parse the input date and time
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = inputFormat.parse(inputDateTime);

            // Format the date and time to "hh:mm"
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "Invalid Format";
        }
    }

    @Override
    public String toString() {
        return taskID + ":::" + orderID + ":::"
                + pickUpAddress + ":::" + destinationAddress + ":::"
                + customerName + ":::" + customerID + ":::"
                + shopName + ":::" + getDeliveryFee() + ":::"
                + requestedPickUpTime + ":::" + requestedDeliveryTime;
    }

    public static void appendTaskToDelivery(Task task) {
        String filePath = "src/textFile/delivery.txt";
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        fileHandlerImp.writeFileNewLine(filePath, task.toString());
    }

    public static void appendTask(String task) {
        String filePath = "src/textFile/task.txt";
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        fileHandlerImp.writeFileNewLine(filePath, task);

        // Append to Runner Task as well
        RunnerTask.appendRunnerTask(task.split(":::")[0]);
    }
}
