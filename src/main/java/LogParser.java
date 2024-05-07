import java.util.*;

public class LogParser {
    public static List<ParsedData> parseLogs(List<String> logDataList) {
        List<ParsedData> parsedDataList = new ArrayList<>();

        for (String logData : logDataList) {
            String[] section = logData.split("\\]\\[");                             // 로그 항목 분할

            // 섹션별 대괄호 제거
            String statusCodeString = section[0].replace("[", "");
            String url = section[1];
            String browser = section[2].replace("]", "");

            int statusCode = Integer.parseInt(statusCodeString);                            // 상태 코드 정수형으로 파싱

            // 인덱싱 활용 API Key, API Service ID 분리
            String[] urlPart = url.split("\\?apikey=");
            String apiServiceId = urlPart[0].replace("http://apis.daum.net/search/", "");
            String apiKey = urlPart[1].split("&")[0];

            parsedDataList.add(new ParsedData(statusCode, apiKey, apiServiceId, browser));     // ParsedData 객체 생성
        }
        return parsedDataList;
    }
}
