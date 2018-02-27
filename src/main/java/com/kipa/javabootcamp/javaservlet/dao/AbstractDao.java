package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T> {
    EntityManager entityManager;

    public void create(T o) {
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(o);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void update(T o) {
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void delete(T o) {
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(o);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
