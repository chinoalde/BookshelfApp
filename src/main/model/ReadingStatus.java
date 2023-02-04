package model;

public enum ReadingStatus {
    WANT_TO_READ("want to read"),
    READING("reading"),
    READ("read");

    private String key;

    ReadingStatus(String status) {
        key = status;
    }

    public String getStatus() {
        return this.key;
    }

}
