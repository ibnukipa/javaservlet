package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Employee;
import com.kipa.javabootcamp.javaservlet.util.CastHelperUtil;
import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDao {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    public EmployeeDao(){}

    public void create(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = null;
        try {
            entityManager.getTransaction().begin();
            employees = CastHelperUtil.listAndCast(entityManager.createQuery("select employee from Employee employee"));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(Integer id) {
        Employee employee = null;
        try {
            entityManager.getTransaction().begin();
            employee = entityManager.find(Employee.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }

    public Employee authenticate(String username, String password) {
        Employee employee = null;
        try {
            entityManager.getTransaction().begin();
            employee = entityManager.createQuery("from Employee where username='"+username+"' and password='"+password+"'", Employee.class).getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }
}
