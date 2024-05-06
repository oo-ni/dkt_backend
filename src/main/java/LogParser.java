import java.util.ArrayList;
import java.util.List;

public class LogParser {
    public static List<ParsedData> parseLogs(List<String> logDataList) {
        List<ParsedData> parsedDatas = new ArrayList<>();

        for (String logData : logDataList) {
            String[] section = logData.split("\\]\\[");                             // 로그 항목 분할

            // 섹션별 대괄호 제거
            String statusCode = section[0].replace("[", "");
            String url = section[1];
            String browser = section[2].replace("]", "");

            // API Key, API Service ID 추출
            String[] urlPart = url.split("\\?apikey=");
            String apiServiceId = urlPart[0].replace("http://apis.daum.net/search/", "");
            String apiKey = urlPart[1].split("&")[0];

            parsedDatas.add(new ParsedData(statusCode, apiKey, apiServiceId, browser));     // ParsedData 객체 생성
        }
        return parsedDatas;
    }
}
