import java.util.*;
import java.util.stream.Collectors;

public class LogAnalyzer {
    public static AnalyzedData analyzeLogs(List<ParsedData> parsedDataList) {
        // 각 변수 별 호출 횟수 저장 객체
        Map<String, Integer> apiKeyUnit = new HashMap<>();
        Map<String, Integer> apiServiceIdUnit = new HashMap<>();
        Map<String, Integer> browserUnit = new HashMap<>();

        for (ParsedData parsedData : parsedDataList) {
            // getOrDefault활용 parsedData에서 Key값 가져와서 객체에 put
            apiKeyUnit.put(
                    parsedData.getApiKey(),
                    apiKeyUnit.getOrDefault(parsedData.getApiKey(), 0) + 1
            );
            apiServiceIdUnit.put(
                    parsedData.getApiServiceId(),
                    apiServiceIdUnit.getOrDefault(parsedData.getApiServiceId(), 0) + 1
            );
            browserUnit.put(
                    parsedData.getBrowser(),
                    browserUnit.getOrDefault(parsedData.getBrowser(), 0) + 1
            );
        }

        // 최다 호출 API Key - mostCalledApiKey
        String mostCalledApiKey = apiKeyUnit.entrySet().stream()                                        // entrySet(key(API Key), value(호출 횟수)) stream 생성
                .max(Map.Entry.comparingByValue())                                                      // value 기준으로 최대값 판별
                .map(Map.Entry::getKey)                                                                 // key 매핑
                .orElse("");                                                                      // Optional 처리

        // 상위 3개 API Service ID - topApiServiceIds
        List<Map.Entry<String, Integer>> topApiServiceIds = apiServiceIdUnit.entrySet().stream()        // entrySet(key(API Service ID), value(호출 횟수)) stream 생성
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())                       // value 기준으로 정렬
                .limit(3)                                                                       // Top 3
                .collect(Collectors.toList());                                                          // List로 stream 변환

        // 웹 브라우저 별 사용 비율 - usageBrowserRatios
        int totalBrowserCalls = browserUnit.values().stream()                                           // values(호출 횟수) stream 생성
                .mapToInt(Integer::intValue)                                                            // 정수형 매핑
                .sum();                                                                                 // 전체 브라우저 호출 횟수
        Map<String, String> BrowserRatios = browserUnit.entrySet().stream()                             // entrySet(key(browser), value(호출 횟수)) stream 생성
                .collect(Collectors.toMap(Map.Entry::getKey, value                                      // key 매핑해서 Map으로 stream 변환
                        -> String.format("%d%%", (value.getValue() * 100) / totalBrowserCalls)));       // value 설정(백분위로 포매팅)

        return new AnalyzedData(mostCalledApiKey, topApiServiceIds, BrowserRatios);
    }
}
