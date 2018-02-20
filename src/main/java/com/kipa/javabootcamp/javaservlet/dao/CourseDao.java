package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Course;
import com.kipa.javabootcamp.javaservlet.util.CastHelperUtil;
import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CourseDao {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    public CourseDao(){}

    public void create(Course course) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
