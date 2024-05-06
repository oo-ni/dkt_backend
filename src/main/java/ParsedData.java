public class ParsedData {
    private final int statusCode;
    private final String apiKey;
    private final String apiServiceId;
    private final String browser;

    public ParsedData(int statusCode, String apiServiceId, String apiKey, String browser) {
        this.statusCode = statusCode;
        this.apiKey = apiKey;
        this.apiServiceId = apiServiceId;
        this.browser = browser;
    }

    public int getStatusCode() {
        return statusCode;
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

