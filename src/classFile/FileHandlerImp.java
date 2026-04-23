package classFile;

import interfaceFile.FileHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerImp implements FileHandler {

    public static FileHandlerImp getFileHandlerImpInstance() {
        return new FileHandlerImp();
    }

    @Override
    public String createTextFile(String fileName) {
        File src = new File("src\\textFile");

        src.mkdirs();
        File file = new File(src, fileName);

        boolean b = false;
        try {
            b = file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error register user information: " + e.getMessage());
        }

        if (b || file.exists()) {
            return file.getPath();
        } else {
            return null;
        }
    }

    @Override
    public boolean writeFileNewLine(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error register user information: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean writeWholeFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String readFileLine(String filePath, String key) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<String[]> readUntilFindFileLine(String filePath, String key) {
        List<String[]> lines = new ArrayList<>();
        boolean find = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    String[] data = line.split(":::");
                    lines.add(data);
                    find = true; // UPDATED : remove break;
                } else {
                    // UPDATED : remove lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return find ? lines : null;
    }

    @Override
    public List<String> readKeyFileLines(String filePath, String key) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lines;
    }

    @Override
    public List<String> readWholeFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lines;
    }

    @Override
    public List<String[]> readKeyLinesData(String filePath, String key) {
        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    String[] data = line.split(":::");
                    dataList.add(data);
                }
            }
        } catch (IOException e) {
            System.err.println(("Error reading the file: " + e.getMessage()));
        }
        return dataList;
    }

    @Override
    public List<String[]> readWholeData(String filePath) {
        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":::");
                dataList.add(data);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return dataList;
    }

    @Override
    public boolean updateFileLine(String filePath, String key, String updatedContent) {
        List<String> lines = new ArrayList<>();
        boolean find = false;

        //read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //find the line containing the key
                if (line.contains(key)) {
                    //update the line
                    lines.add(updatedContent);
                    find = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        if (find) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            }
        }

        return find;
    }

    @Override
    public String deleteFileLine(String filePath, String key) {
        List<String> lines = new ArrayList<>();
        String deletedLine = null;
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    deletedLine = line;
                    found = true;
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            }
        }

        return found ? deletedLine : "false";
    }
}
