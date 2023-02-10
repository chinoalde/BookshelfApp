package model;


// different reading status' that a book can have
public enum ReadingStatus {
    WANT_TO_READ("Want to Read"),
    READING("Reading"),
    READ("Read");

    private String status;

    ReadingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
