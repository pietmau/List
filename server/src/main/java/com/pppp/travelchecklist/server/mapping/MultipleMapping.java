package com.pppp.travelchecklist.server.mapping;

import java.util.List;

public class MultipleMapping {
    public String item = null;
    public List<String> tags = null;

    public MultipleMapping(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "MultipleMapping{" +
                "item='" + item + '\'' +
                ", tags=" + tags +
                '}';
    }
}
