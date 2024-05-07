public class ParsedData {
    private final String apiKey;
    private final String apiServiceId;
    private final String browser;

    public ParsedData(String apiServiceId, String apiKey, String browser) {
        this.apiKey = apiKey;
        this.apiServiceId = apiServiceId;
        this.browser = browser;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiServiceId() {
        return apiServiceId;
    }

    public String getBrowser() {
        return browser;
    }
}

