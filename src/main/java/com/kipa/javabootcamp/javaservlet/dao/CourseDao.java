package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Course;
import com.kipa.javabootcamp.javaservlet.model.Employee;
import com.kipa.javabootcamp.javaservlet.util.CastHelperUtil;
import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CourseDao extends AbstractDao<Course> {
    public CourseDao(){}

    public List<Course> getCourses() {
        List<Course> courses = null;
        try {
            entityManager.getTransaction().begin();
            courses = CastHelperUtil.listAndCast(entityManager.createQuery("select course from Course course"));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Course getCourseById(Integer id) {
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;
    }
}
