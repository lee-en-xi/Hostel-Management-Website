package interfaceFile;
import java.util.List;

public interface FileHandler {
    public abstract  String createTextFile(String fileName);

    public abstract boolean writeFileNewLine(String filePath, String content);

    public abstract boolean writeWholeFile(String filePath, List<String> contents);

    public abstract String readFileLine(String filePath, String key);
    
    public abstract List<String[]> readUntilFindFileLine(String filePath, String key);

    public abstract List<String> readKeyFileLines(String filePath, String key);

    public abstract List<String> readWholeFile(String filePath);

    public abstract List<String[]> readKeyLinesData(String filePath, String key);

    public abstract  List<String[]> readWholeData(String filePath);

    public abstract boolean updateFileLine(String filePath, String key, String content);

    public abstract String deleteFileLine(String filePath, String key);
}
