package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Course;
import com.kipa.javabootcamp.javaservlet.util.CastHelperUtil;
import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import java.util.List;

public class CourseDao extends AbstractDao<Course> {
    public CourseDao(){ }

    public List<Course> getCourses() {
        List<Course> courses = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            courses = CastHelperUtil.listAndCast(entityManager.createQuery("select course from Course course"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Course getCourseById(Integer id) {
        Course course = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            course = entityManager.find(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;
    }
}
