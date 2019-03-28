package com.pppp.travelchecklist.server.mapping;

/**
 * To play nicely with the AWS backend
 */
public class Mapping {
    public String item = null;
    public String tag = null;

    public Mapping() {
    }

    public Mapping(String item, String tag) {
        this.item = item;
        this.tag = tag;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "item='" + item + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
