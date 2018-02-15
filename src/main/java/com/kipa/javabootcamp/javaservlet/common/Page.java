package com.kipa.javabootcamp.javaservlet.common;

import java.util.ArrayList;

public class Page {
    private String title;
    private String path;

    public Page(String title) {
        this.setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
