import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TestLogParser {
    @Test
    public void testParseLogs() {
        List<String> logDataList = new ArrayList<>();
        logDataList.add("[200][http://apis.daum.net/search/news?apikey=anw1&q=daum][IE][2012-06-10 08:30:51]");

        List<ParsedData> result = LogParser.parseLogs(logDataList);

        ParsedData parsedData = result.get(0);

        assertEquals("anw1", parsedData.getApiKey(), "API Key 매칭 성공");
        assertEquals("news", parsedData.getApiServiceId(), "API Service ID 매칭 성공");
        assertEquals("IE", parsedData.getBrowser(), "Browser 매칭 성공");
    }
}
