import java.util.*;
public class AnalyzedData {
    private final String mostCalledApiKey;
    private final List<Map.Entry<String, Integer>> topApiServiceIds;
    private final Map<String, String> browserRatios;

    public AnalyzedData(String mostCalledApiKey, List<Map.Entry<String, Integer>> topServiceIds, Map<String, String> browserRatios) {
        this.mostCalledApiKey = mostCalledApiKey;
        this.topApiServiceIds = topServiceIds;
        this.browserRatios = browserRatios;
    }

    public String getMostCalledApiKey() {
        return mostCalledApiKey;
    }

    public List<Map.Entry<String, Integer>> getTopApiServiceIds() {
        return topApiServiceIds;
    }

    public Map<String, String> getBrowserRatios() {
        return browserRatios;
    }
}
