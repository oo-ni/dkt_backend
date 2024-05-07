import java.io.*;
import java.util.*;

// 분석 결과를 출력 파일에 기록하는 클래스
public class LogFileWriter {
    public static void writeLogFile(String outputFile, AnalyzedData result) {
        // AnalyzedData에서 getter 불러와서 BufferedWriter write로 쓰기
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("최다호출 API KEY\n" + result.getMostCalledApiKey() + "\n");
            bw.write("\n상위 3개의 API Service ID와 각각의 요청 수\n");
            for (Map.Entry<String, Integer> entry : result.getTopApiServiceIds()) {
                bw.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            bw.write("\n웹브라우저별 사용 비율\n");
            for (Map.Entry<String, String> entry : result.getBrowserRatios()) {
                bw.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
