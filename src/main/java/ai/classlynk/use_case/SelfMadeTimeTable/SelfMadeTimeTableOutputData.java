package ai.classlynk.use_case.SelfMadeTimeTable;

import ai.classlynk.entity.Timetable;


public class SelfMadeTimeTableOutputData {
    private boolean success;
    private String message;
    private Timetable updatedTimeTable;

    public SelfMadeTimeTableOutputData(boolean success, String message, Timetable updatedTimeTable) {
        this.success = success;
        this.message = message;
        this.updatedTimeTable = updatedTimeTable;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Timetable getUpdatedCart() {
        return updatedTimeTable;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSelfMadeTimeTable(Timetable updatedTimeTable) { this.updatedTimeTable = updatedTimeTable; }
}

