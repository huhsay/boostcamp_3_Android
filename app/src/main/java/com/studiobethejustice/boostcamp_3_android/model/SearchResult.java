package com.studiobethejustice.boostcamp_3_android.model;

import java.util.ArrayList;

public class SearchResult {

    private String lastBuildDate;
    private String postdate;
    private int total;
    private int start;
    private int display;
    private ArrayList<Item> items = new ArrayList<>();

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public ArrayList<Item> getItem() {
        return items;
    }

    public void setItem(ArrayList<Item> item) {
        items = item;
    }
}
