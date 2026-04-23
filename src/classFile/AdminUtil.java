package classFile;

import javax.swing.*;
import java.util.List;

public class AdminUtil {
    public static final String defaultPassword = "abcd1234";

    private static final FileHandlerImp fileManagerImp = FileHandlerImp.getFileHandlerImpInstance();

    //Get the role of the user
    public static String getUserRole(String filePath, String key) {
        String line = fileManagerImp.readFileLine(filePath, key);
        if (line == null) {
            return null;
        }
        String[] data = line.split(":::");
        return data[data.length - 1].trim();
    }


    public static String readSequence(String role) {
        String result = null;
        FileHandlerImp fileManagerImp = FileHandlerImp.getFileHandlerImpInstance();

        String filePath = fileManagerImp.createTextFile("role_sequence.txt");

        String title = "Generate ID(Read Sequence)";
        String message = "File Creation Fails";

        if (filePath == null) {
            JOptionPane.showMessageDialog(null, title, message, JOptionPane.WARNING_MESSAGE);
        } else {
            List<String> lines = fileManagerImp.readWholeFile(filePath);
            if (lines.isEmpty()) {
                String content = "customer:::000001\n" +
                        "vendor:::000001\n" +
                        "runner:::000001\n" +
                        "admin:::000001\n";
                fileManagerImp.writeFileNewLine(filePath,content);
            }
            String line = fileManagerImp.readFileLine(filePath, role);
            String[] data = line.split(":::");
            result = data[1].trim();

            int sequence = Integer.parseInt(result) + 1;
            String formattedSequence = java.lang.String.format("%06d", sequence);
            String content = role + ":::" + formattedSequence;
            boolean b = fileManagerImp.updateFileLine(filePath, role, content);
            if(!b) {
                message = "File Updated Unsuccessful";
                JOptionPane.showMessageDialog(null, title, message, JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }

    public static String generateID(String role) {
        String prefix = switch (role) {
            case "admin" -> "a";
            case "customer" -> "c";
            case "vendor" -> "v";
            case "runner" -> "r";
            default -> null;
        };

        String suffix = readSequence(role);

        return prefix + suffix;
    }

    //Validate ID
    public static boolean isId(String id) {
        return id.matches("[a-zA-Z]+\\d{3,}");
    }

    //Validate Username
    public static boolean isUsername(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    //Validate Email Format
    public static boolean isEmail(String email) {
        //return email.matches("\\w+@\\w+(\\.[a-zA-Z]){1,3}");
        return email.matches("[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}");
    }

    //Validate Phone Number
    public static boolean isPhoneNumber(String phoneNumber) { 
        return phoneNumber.matches("6?0\\d{9,10}");
    }
    
    //Validate DOB
    public static boolean isDOB (String dobStr) {
        return dobStr.matches("(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])");
    }
}
