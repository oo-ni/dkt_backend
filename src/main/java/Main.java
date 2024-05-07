import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "input.log";
        String outputFile = "output.log";

        List<String> logLineList = LogFileReader.readLogFile(inputFile);            // 로그 읽어오기

        List<ParsedData> parsedDataList = LogParser.parseLogs(logLineList);         // 로그 파싱

        AnalyzedData result = LogAnalyzer.analyzeLogs(parsedDataList);              // 로그 분석

        LogFileWriter.writeLogFile(outputFile, result);                             // 분석 결과 출력
    }
}
