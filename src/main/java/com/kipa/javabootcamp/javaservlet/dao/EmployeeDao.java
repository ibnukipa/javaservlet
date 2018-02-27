package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Employee;
import com.kipa.javabootcamp.javaservlet.util.CastHelperUtil;
import com.kipa.javabootcamp.javaservlet.util.JpaUtil;

import java.util.List;

public class EmployeeDao extends AbstractDao<Employee> {
    public EmployeeDao(){ }

    public List<Employee> getEmployees() {
        List<Employee> employees = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            employees = CastHelperUtil.listAndCast(entityManager.createQuery("select employee from Employee employee"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(Integer id) {
        Employee employee = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            employee = entityManager.find(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public Employee authenticate(String username, String password) {
        Employee employee = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            employee = entityManager.createQuery("from Employee where username='"+username+"' and password='"+password+"'", Employee.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }
}
