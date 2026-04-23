package classFile;

import enumFile.*;
import interfaceFile.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RunnerTask implements IDHandler {

    private final FileHandlerImp fileHandlerImp;
    private static String filePath = "src/textFile/runner_task.txt";

    private String runnerTaskID;
    private Runner runner;
    private String taskID;
    private Response response;
    private String pickUpTime;
    private String deliveryTime;
    private TaskStatus taskStatus;
    private String rating;
    private String acceptTime;

    public RunnerTask() {
        fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        this.runnerTaskID = generateID();
        this.runner = new Runner();
        this.taskID = "Unspecified";
        this.response = Response.Unspecified;
        this.pickUpTime = "Unspecified";
        this.deliveryTime = "Unspecified";
        this.taskStatus = TaskStatus.Unspecified;
        this.rating = "Unspecified";
        this.acceptTime = "Unspecified";
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public static void updateDeliveryTime(String runnerTaskID) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        //Get the Current Time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentTime = dateFormat.format(new Date());

        // Update Value
        String[] data = line.split(":::");
        data[5] = currentTime;

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    public static void updatePickUpTime(String runnerTaskID) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        //Get the Current Time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentTime = dateFormat.format(new Date());

        // Update Value
        String[] data = line.split(":::");
        data[4] = currentTime;

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    public static void updateResponse(String runnerTaskID, Response response) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        // Update Value
        String[] data = line.split(":::");
        data[3] = response + ""; // Convert to String

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    public static void updateTaskStatus(String runnerTaskID, TaskStatus taskStatus) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        // Update Value
        String[] data = line.split(":::");
        data[6] = taskStatus + ""; // Convert to String

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    public static void updateRating(String runnerTaskID, String rating) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        // Update Value
        String[] data = line.split(":::");
        data[7] = rating;

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    public static void updateAcceptTime(String runnerTaskID) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerTaskID);
        if (line == null) {
            return;
        }

        //Get the Current Time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentTime = dateFormat.format(new Date());

        // Update Value
        String[] data = line.split(":::");
        data[8] = currentTime;

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerTaskID, result);
    }

    @Override
    public String generateID() {
        String filePath = "src/textFile/runner_task.txt";

        boolean isDuplicate;
        int numID;
        String ID;

        // Read Whole File Data
        List<String[]> datalist = fileHandlerImp.readWholeData(filePath);
        // Get the ID of the last rows of Data and extract the only Number
        if (datalist.isEmpty()) {
            ID = "RT00001";
        } else {
            ID = datalist.get(datalist.size() - 1)[0].replace("RT", "");
            numID = Integer.parseInt(ID);

            do {
                // Increase the ID index
                numID = numID + 1;

                //Compensate for vacancies
                String vac = "";
                if (numID < 10000 && numID >= 1000) {
                    vac = "0";
                } else if (numID < 1000 && numID >= 100) {
                    vac = "00";
                } else if (numID < 100 && numID >= 10) {
                    vac = "000";
                } else if (numID < 10) {
                    vac = "0000";
                }

                // Combine all to String
                Integer.toString(numID);
                ID = "RT" + vac + numID;

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
    

   
    @Override
    public String toString() {
        return runnerTaskID + ":::"
                + runner.getUserID() + ":::"
                + taskID + ":::"
                + response + ":::"
                + pickUpTime + ":::"
                + deliveryTime + ":::"
                + taskStatus + ":::"
                + rating + ":::"
                + acceptTime;
    }

    public static void appendRunnerTask(String taskID) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        // Write Each RunnerTask to File
        String filePath1 = "src/textFile/runner.txt";
        List<String[]> datalist = fileHandlerImp.readWholeData(filePath1);
        for (String[] lines : datalist) {
            Runner runners = new Runner(lines);

            // Write Each Lines to File
            String filePath2 = "src/textFile/runner_task.txt";
            RunnerTask rt = new RunnerTask();
            rt.setRunner(runners);
            rt.setTaskID(taskID);
            fileHandlerImp.writeFileNewLine(filePath2, rt.toString());
        }
    }

    public static String[] getRevenue(String runnerID, String type, int yearsToAddOrSubtract, int monthsToAddOrSubtract, int daysToAddOrSubtract) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.now()
                .plusYears(yearsToAddOrSubtract)
                .plusMonths(monthsToAddOrSubtract)
                .plusDays(daysToAddOrSubtract);

        LocalDate date1 = dateTime1.toLocalDate();
        Month month1 = dateTime1.getMonth();
        Year year1 = Year.from(dateTime1);

        double deliveryFee = 0, hoursWork = 0, sum_rating = 0;
        int numberofTask = 0, count = 0;

        List<String[]> splitData = fileHandlerImp.readUntilFindFileLine(filePath, runnerID);
        if (splitData == null) {
            return new String[]{
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                "-"
            };
        }
        for (String[] splitArray : splitData) {
            if (!splitArray[8].equals("Unspecified")) {
                LocalDateTime dateTime2 = LocalDateTime.parse(splitArray[8], dateTimeFormatter);
                boolean dateEqual = false;

                switch (type) {
                    case "Daily":
                        dateEqual = date1.isEqual(dateTime2.toLocalDate());
                        break;
                    case "Monthly":
                        dateEqual = month1 == dateTime2.getMonth() && year1.equals(Year.from(dateTime2));
                        break;
                    default: // Assuming "Yearly"
                        dateEqual = year1.equals(Year.from(dateTime2));
                        break;
                }

                if (dateEqual) {
                    String taskDataLine = fileHandlerImp.readFileLine("src/textFile/task.txt", splitArray[2]);
                    String[] taskData = taskDataLine.split(":::");
                    deliveryFee += Double.parseDouble(taskData[7]);
                    numberofTask++;

                    if (!"Unspecified".equals(splitArray[5])) {
                        LocalDateTime acceptTime = LocalDateTime.parse(splitArray[8], dateTimeFormatter);
                        LocalDateTime deliveryTime = LocalDateTime.parse(splitArray[5], dateTimeFormatter);
                        hoursWork += Duration.between(acceptTime, deliveryTime).toMillis() / 3600000.0; // Convert to hours
                    }

                    if (!"Unspecified".equals(splitArray[7])) {
                        count++;
                        sum_rating += Double.parseDouble(splitArray[7]);
                    }
                }
            }
        }

        String avg_rating = (count != 0) ? String.format("%.2f", sum_rating / count) : "0.00";

        return new String[]{
            String.format("%.2f", deliveryFee),
            String.valueOf(numberofTask),
            String.format("%.2f", hoursWork),
            avg_rating,
            String.valueOf(dateTime1.getDayOfMonth()),
            dateTime1.getMonth().toString(),
            String.valueOf(year1)
        };
    }

}
