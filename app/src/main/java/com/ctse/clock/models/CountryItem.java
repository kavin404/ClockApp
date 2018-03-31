package com.ctse.clock.models;

/**
 * Created by Kavindu on 3/31/2018.
 */

public class CountryItem {
    private String head;
    private boolean isChecked;
    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public CountryItem(String head, String timezone ,boolean isChecked) {
        this.head = head;
        this.timezone = timezone;
        this.isChecked = isChecked;
    }

    public String getHead() {
        return head;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
