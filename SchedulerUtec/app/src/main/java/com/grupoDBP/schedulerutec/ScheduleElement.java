package com.grupoDBP.schedulerutec;

public class ScheduleElement {
    public String title;
    public String createdBy;
    public String date;

    public ScheduleElement(String title, String createdBy, String date) {
        this.title = title;
        this.createdBy = createdBy;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
