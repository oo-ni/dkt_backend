import java.io.*;
import java.util.*;

// 전체 로그 파일을 읽어와서 줄을 반환하는 클래스
public class LogFileReader {
    public static List<String> readLogFile(String inputFile) {
        List<String> logLineList = new ArrayList<>();                                  // logLines에 각 라인 저장 후 Arraylist로 반환
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {   // BuffererReader readLine으로 읽어오기
            String line;
            while ((line = br.readLine()) != null) {
                logLineList.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return logLineList;
    }
}
