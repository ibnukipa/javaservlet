package com.kipa.javabootcamp.javaservlet.controller;

import com.kipa.javabootcamp.javaservlet.common.Breadcrumb;
import com.kipa.javabootcamp.javaservlet.common.Constanta;
import com.kipa.javabootcamp.javaservlet.common.Message;
import com.kipa.javabootcamp.javaservlet.common.Page;
import com.kipa.javabootcamp.javaservlet.dao.EmployeeDAO;
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
    private static final long serialVersionUID = 1L;
    private static EmployeeDAO employeeDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        employeeDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);
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

    private Employee convertRequestToEmployee(HttpServletRequest request) {
        String code = request.getParameter("employee_code");
        String username = request.getParameter("employee_username");
        String password = request.getParameter("employee_password");
        String name = request.getParameter("employee_name");
        String address = request.getParameter("employee_address");
        String phone = request.getParameter("employee_phone");
        String grade = request.getParameter("employee_grade");
        String stream = request.getParameter("employee_stream");

        return new Employee(code, username, password, name, address, phone, grade, stream);
    }

    private void getEmployees(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Employee> employees = employeeDAO.listAllEmployees();

        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Employee", null, "users"));
        }});
        request.setAttribute("page", new Page("Employee | ".concat(Constanta._APP_NAME)) {{setPath("employee");}});
        request.setAttribute("employees", employees);

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void getEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));
        final Employee employee = employeeDAO.getEmployee(employeeId);

        if(employee == null) {
            handleNotFound(request, response);
        } else {
            request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
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
        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Employee", "/employee", "users"));
            add(new Breadcrumb("Create", null, "users"));
        }});
        request.setAttribute("page", new Page("Create Employee | " + Constanta._APP_NAME) {{setPath("employee/create");}});

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void postCreateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Employee theEmployee = convertRequestToEmployee(request);
        employeeDAO.insertEmployee(theEmployee);
        request.setAttribute("message", new Message(
            "Employee "+ theEmployee.getName() +" has been CREATED",
            "#"+theEmployee.getCode()+" - "+theEmployee.getName()+" ("+theEmployee.getGrade()+" - "+theEmployee.getStream()+")",
            "success",
            "mini",
            "checkmark"));

        getEmployees(request, response);
    }

    private void getUpdateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));
        final Employee employee = employeeDAO.getEmployee(employeeId);

        if(employee == null) {
            handleNotFound(request, response);
        } else {
            request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
                add(new Breadcrumb("Home", "/", "home"));
                add(new Breadcrumb("Employee", "/employee", "users"));
                add(new Breadcrumb(employee.getName().concat(" ("+ employee.getCode() +")"), null, "user"));
            }});
            request.setAttribute("page", new Page(employee.getName()+ " | " + Constanta._APP_NAME) {{setPath("employee/update");}});
            request.setAttribute("employee", employee);

            String path = getTemplatePath("/");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }

    private void postUpdateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Employee theEmployee = convertRequestToEmployee(request);
        theEmployee.setId(Integer.parseInt(request.getParameter("employee_id")));
        employeeDAO.updateEmployee(theEmployee);

        request.setAttribute("message", new Message(
                "Employee "+ theEmployee.getName() +" has been UPDATED",
                "#"+theEmployee.getCode()+" - "+theEmployee.getName()+" ("+theEmployee.getGrade()+" - "+theEmployee.getStream()+")",
                "success",
                "mini",
                "checkmark"));

        getEmployees(request, response);
    }

    private void postDeleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Integer employeeId = Integer.parseInt(request.getParameter("id"));

        Employee employee = employeeDAO.getEmployee(employeeId);

        if (employee == null) {
            handleNotFound(request, response);
        } else {
            employeeDAO.deleteEmployee(employee);
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
