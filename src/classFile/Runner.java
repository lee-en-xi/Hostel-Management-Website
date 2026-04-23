package classFile;

import GUIDesignRunner.RunnerFrame;
import enumFile.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner extends User {
    private static final FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();;
    private String workStatus;
    
    public Runner () {
    }

    public Runner (String username, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(username, email, phoneNumber, dobStr, gender, role);
        this.workStatus = "offDuty";
    }

    public Runner (String userID, String username, String password, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(userID, username, password, email, phoneNumber, dobStr, gender, "runner");
        this.workStatus = "offDuty";
    }
    
    public Runner (String[] lines) {
        super(lines[0], lines[1], null, lines[2], null, "2023-10-24", null, "runner");
        this.workStatus = lines[3];
        
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public void login (String userID) {
        new RunnerFrame(userID).setVisible(true);
    }

    @Override
    public String toString () {
        return getUserID() + ":::" +
                getUsername() + ":::" +
                getEmail() + ":::" +
                getWorkStatus();
    }
    
    public static Runner readRunnerProfile(String runnerID) {
        Runner runner = new Runner();
        String userFilePath = "src/textFile/user.txt";
        String runnerFilePath = "src/textFile/runner.txt";
        
        String userLine = fileHandlerImp.readFileLine(userFilePath, runnerID);
        String runnerLine = fileHandlerImp.readFileLine(runnerFilePath, runnerID);
        
        if (userLine != null && runnerLine != null) {
            String[] userData = userLine.split(":::"); //0-id, 1-name, 2-pw, 3-email, 4-phone.no, 5-dob, 6-gender, 7-role
            String[] runnerData = runnerLine.split(":::");

            runner.setUserID(userData[0]);
            runner.setUsername(userData[1]);
            runner.setPassword(userData[2]);
            runner.setEmail(userData[3]);
            runner.setPhoneNumber(userData[4]);
            runner.setDob(userData[5]);
            runner.setGender(userData[6]);
            runner.setRole(userData[7]);
            runner.setWorkStatus(runnerData[runnerData.length - 1]);
        }
        
        return runner;
    }
    
    public void updateWorkStatus (String runnerID, WorkStatus workStatus) {
        String filePath = "src/textFile/runner.txt";
        
        // Read Relevant Line
        String line = fileHandlerImp.readFileLine(filePath, runnerID);
        if (line == null) {
            return;
        }
        
        // Change Work Status
        String[] data = line.split(":::");
        if (workStatus.equals(WorkStatus.onDuty)) {
            data[3] = WorkStatus.onDuty + ""; //Convert to String
        } else {
            data[3] = WorkStatus.offDuty + "";//Convert to String
        }

        // Update data to File
        List<String> updatedLine = new ArrayList<>();
        updatedLine.addAll(Arrays.asList(data));
        String result = String.join(":::", updatedLine);
        fileHandlerImp.updateFileLine(filePath, runnerID, result);
    }
    
    public static Boolean haveAvailableRunner () {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        
        // Check whether have available Runner
        List<String[]> runnerData = fileHandlerImp.readWholeData("src/textFile/runner.txt");
        Boolean haveAvailableRunner = false;
        for (String[] lines : runnerData) {
            if (lines[3].equals(WorkStatus.onDuty + "")) {
                haveAvailableRunner = true;
            }
        }
        
        return haveAvailableRunner;
    }
    
    public static Boolean notAllRunnerDecline(String taskID) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();

        // Check whether all Runner decline the task
        List<String[]> runnerTaskData = fileHandlerImp.readUntilFindFileLine("src/textFile/runner_task.txt", taskID);
        Boolean notAllDecline = false;
        for (String[] lines : runnerTaskData) {
            if (!lines[3].equals("Decline")) {
                notAllDecline = true;
            }
        }

        return notAllDecline;
    }
}