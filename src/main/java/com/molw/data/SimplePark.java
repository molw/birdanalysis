package com.molw.data;

public class SimplePark {
    private String name = "";
    private int count = 0;

    public SimplePark() {
    }

    public SimplePark(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
