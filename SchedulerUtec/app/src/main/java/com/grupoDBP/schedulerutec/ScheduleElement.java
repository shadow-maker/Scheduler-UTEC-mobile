package com.grupoDBP.schedulerutec;

public class ScheduleElement {
    public String id;
    public String title;
    public String createdBy;

    public ScheduleElement(String id, String title, String createdBy) {
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
