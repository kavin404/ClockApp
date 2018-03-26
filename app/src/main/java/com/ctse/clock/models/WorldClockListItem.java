package com.ctse.clock.models;

public class WorldClockListItem {
    private String head;
    private String desc;
    private boolean is24;

    public WorldClockListItem(String head, String desc, boolean is24) {
        this.head = head;
        this.desc = desc;
        this.is24 = is24;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
