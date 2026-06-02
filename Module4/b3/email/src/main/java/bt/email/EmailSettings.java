package bt.email;

public class EmailSettings {
    private String language;
    private int pageSize;
    private boolean spamFilter;
    private String signature;

    public EmailSettings() {
        this.language = "English";
        this.pageSize = 25;
        this.spamFilter = false;
        this.signature = "";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpamFilter() {
        return spamFilter;
    }

    public void setSpamFilter(boolean spamFilter) {
        this.spamFilter = spamFilter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
