package com.kipa.javabootcamp.javaservlet.common;

public class Breadcrumb {
    private String name;
    private String url;
    private String icon;
    private Breadcrumb child;

    public Breadcrumb(String name, String url, String icon) {
        this.setName(name);
        this.setUrl(url);
        this.setIcon(icon);
        this.setChild(null);
    }

    public Breadcrumb(String name, String url, String icon, Breadcrumb child) {
        this.setName(name);
        this.setUrl(url);
        this.setIcon(icon);
        this.setChild(child);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Breadcrumb getChild() {
        return child;
    }

    public void setChild(Breadcrumb child) {
        this.child = child;
    }
}
