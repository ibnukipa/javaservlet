package com.kipa.javabootcamp.javaservlet.model;

import com.kipa.javabootcamp.javaservlet.listener.AuditListener;
import com.kipa.javabootcamp.javaservlet.unit.Audit;
import com.kipa.javabootcamp.javaservlet.unit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employee")
@EntityListeners(AuditListener.class)
public class Employee implements Auditable, Serializable {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Audit audit;

    @Column(name = "employee_code")
    private String code;

    @Column(name = "employee_username")
    private String username;

    @Column(name = "employee_password")
    private String password;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_address")
    private String address;

    @Column(name = "employee_phone")
    private String phone;

    @Column(name = "employee_grade")
    private String grade;

    @Column(name = "employee_stream")
    private String stream;

    @OneToMany(mappedBy="courseBy",targetEntity=Course.class,
            fetch=FetchType.EAGER)
    private Collection classes;

    @ManyToMany(mappedBy="participants",fetch=FetchType.EAGER)
    private List<Course> courses;

    public Employee(){}

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

    public Collection getClasses() {
        return classes;
    }

    public void setClasses(Collection classes) {
        this.classes = classes;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Audit getAudit() {
        return this.audit;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
