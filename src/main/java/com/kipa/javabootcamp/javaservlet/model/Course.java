package com.kipa.javabootcamp.javaservlet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code")
    private String code;

    @Column(name = "course_type")
    private String type;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_start", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "course_end", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "course_place")
    private String place;

    @ManyToOne(optional=false)
    @JoinColumn(name="course_by",referencedColumnName="employee_id")
    private Employee courseBy;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="employee_course",
            joinColumns=
            @JoinColumn(name="course_id", referencedColumnName="course_id"),
            inverseJoinColumns=
            @JoinColumn(name="employee_id", referencedColumnName="employee_id")
    )
    private List<Employee> participants;

    public Course(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Employee getCourseBy() {
        return courseBy;
    }

    public void setCourseBy(Employee courseBy) {
        this.courseBy = courseBy;
    }

    public List<Employee> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Employee> participants) {
        this.participants = participants;
    }
}
