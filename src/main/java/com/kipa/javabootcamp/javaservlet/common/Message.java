package com.kipa.javabootcamp.javaservlet.common;

public class Message {
    static private String _ICON = "info";
    static private String _SIZE = "mini";
    static private String _TYPE = "info";
    static private Boolean _CLOSEABLE = true;

    private String title;
    private String description;
    private String icon;
    private String size;
    private String type;
    private Boolean closeable;

    public Message(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
        this.setIcon(_ICON);
        this.setSize(_SIZE);
        this.setType(_TYPE);
        this.setCloseable(_CLOSEABLE);
    }

    public Message(String title, String description, String type) {
        this.setTitle(title);
        this.setDescription(description);
        this.setIcon(_ICON);
        this.setSize(_SIZE);
        this.setType(type);
        this.setCloseable(_CLOSEABLE);
    }

    public Message(String title, String description, String type, String size) {
        this.setTitle(title);
        this.setDescription(description);
        this.setIcon(_ICON);
        this.setSize(size);
        this.setType(type);
        this.setCloseable(_CLOSEABLE);
    }

    public Message(String title, String description, String type, String size, String icon) {
        this.setTitle(title);
        this.setDescription(description);
        this.setIcon(icon);
        this.setSize(size);
        this.setType(type);
        this.setCloseable(_CLOSEABLE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCloseable() {
        return closeable;
    }

    public void setCloseable(Boolean closeable) {
        this.closeable = closeable;
    }
}
