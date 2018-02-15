package com.kipa.javabootcamp.javaservlet.model;

public class Employee {
    private Integer id;
    private String code;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String grade;
    private String stream;

    public Employee() {}

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(
            Integer id, String code, String username, String password,
            String name, String address, String phone, String grade, String stream) {
        this.id = id;
        this.code = code;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.grade = grade;
        this.stream = stream;
    }

    public Employee(
            String code, String username, String password,
            String name, String address, String phone, String grade, String stream) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.grade = grade;
        this.stream = stream;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
