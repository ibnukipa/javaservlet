package com.kipa.javabootcamp.javaservlet.controller;

import com.kipa.javabootcamp.javaservlet.common.Breadcrumb;
import com.kipa.javabootcamp.javaservlet.common.Constanta;
import com.kipa.javabootcamp.javaservlet.common.Message;
import com.kipa.javabootcamp.javaservlet.common.Page;
import com.kipa.javabootcamp.javaservlet.dao.EmployeeDao;
import com.kipa.javabootcamp.javaservlet.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/employee", "/employee/create", "/employee/update", "/employee/delete"})
public class EmployeeServlet extends AbstractServlet {
    private EmployeeDao employeeDao = new EmployeeDao();

    private Employee convertRequestToEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setCode(request.getParameter("employee_code"));
        employee.setUsername(request.getParameter("employee_username"));
        employee.setPassword(request.getParameter("employee_password"));
        employee.setName(request.getParameter("employee_name"));
        employee.setAddress(request.getParameter("employee_address"));
        employee.setPhone(request.getParameter("employee_phone"));
        employee.setGrade(request.getParameter("employee_grade"));
        employee.setStream(request.getParameter("employee_stream"));
        return employee;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if(action.equals("/employee/create")) {
                postCreateEmployee(request, response);
            } else if(action.equals("/employee/update")) {
                postUpdateEmployee(request, response);
            } else if(action.equals("/employee/delete")) {
                postDeleteEmployee(request, response);
            } else {
                handleNotFound(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if(action.equals("/employee/create")) {
                getCreateEmployee(request, response);
            } else if(action.equals("/employee/update")) {
                getUpdateEmployee(request, response);
            } else if(action.equals("/employee/delete")) {
                handleNotFound(request, response);
            } else {
                if(request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) > 0) {
                    getEmployee(request, response);
                } else {
                    getEmployees(request, response);
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void getEmployees(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Employee> employees = employeeDao.getEmployees();

        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Employee", "/employee", "users"));
        }});
        request.setAttribute("page", new Page("Employees | ".concat(Constanta._APP_NAME)) {{setPath("employee");}});
        request.setAttribute("employees", employees);

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void getEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));
        final Employee employee = employeeDao.getEmployeeById(employeeId);

        if(employee == null) {
            handleNotFound(request, response);
        } else {
            request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
                add(new Breadcrumb("Home", "/", "home"));
                add(new Breadcrumb("Employee", "/employee", "users"));
                add(new Breadcrumb(employee.getName().concat(" ("+ employee.getCode() +")"), null, "user"));
            }});
            request.setAttribute("page", new Page(employee.getName().concat(" | ".concat(Constanta._APP_NAME))) {{setPath("employee/detail");}});
            request.setAttribute("employee", employee);

            String path = getTemplatePath("/");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }

    private void getCreateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Employee", "/employee", "users"));
            add(new Breadcrumb("Create", null, "users"));
        }});
        request.setAttribute("page", new Page("Create Employee | " + Constanta._APP_NAME) {{setPath("employee/form");}});

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void postCreateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Employee employee = this.convertRequestToEmployee(request);
        employeeDao.create(employee);
        request.setAttribute("message", new Message(
            "Employee "+ employee.getName() +" has been CREATED",
            "#"+employee.getCode()+" - "+employee.getName()+" ("+employee.getGrade()+" - "+employee.getStream()+")",
            "success",
            "mini",
            "checkmark"));

        response.sendRedirect("/employee");
    }

    private void getUpdateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));
        final Employee employee = employeeDao.getEmployeeById(employeeId);

        if(employee == null) {
            handleNotFound(request, response);
        } else {
            request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
                add(new Breadcrumb("Home", "/", "home"));
                add(new Breadcrumb("Employee", "/employee", "users"));
                add(new Breadcrumb(employee.getName().concat(" ("+ employee.getCode() +")"), null, "user"));
            }});
            request.setAttribute("page", new Page(employee.getName()+ " | " + Constanta._APP_NAME) {{setPath("employee/form");}});
            request.setAttribute("employee", employee);

            String path = getTemplatePath("/");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }

    private void postUpdateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Employee employee = convertRequestToEmployee(request);
        employee.setId(Integer.parseInt(request.getParameter("employee_id")));
        employeeDao.update(employee);

        request.setAttribute("message", new Message(
                "Employee "+ employee.getName() +" has been UPDATED",
                "#"+employee.getCode()+" - "+employee.getName()+" ("+employee.getGrade()+" - "+employee.getStream()+")",
                "success",
                "mini",
                "checkmark"));

        getEmployees(request, response);
    }

    private void postDeleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeDao.getEmployeeById(employeeId);

        if (employee == null) {
            handleNotFound(request, response);
        } else {
            employeeDao.delete(employee);
            request.setAttribute("message", new Message(
                "Employee "+ employee.getName() +" has been DELETED",
                "#"+employee.getCode()+" - "+employee.getName()+" ("+employee.getGrade()+" - "+employee.getStream()+")",
                "success",
                "mini",
                "checkmark"));

            getEmployees(request, response);
        }
    }
}
