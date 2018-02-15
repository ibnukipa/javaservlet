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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet({"/login", "/logout", "/account"})
public class LoginServlet extends AbstractServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        employeeDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if(action.equals("/login")) {
                getLogin(request, response);
            } else if(action.equals("/account")) {
                HttpSession session = request.getSession();
                EmployeeServlet.getEmployee(request, response);
            }else {
                handleNotFound(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if(action.equals("/login")) {
                postLogin(request, response);
            } else if(action.equals("/logout")) {
                postLogout(request, response);
            } else {
                handleNotFound(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void getLogin(HttpServletRequest request, HttpServletResponse response)
        throws  ServletException, IOException, SQLException {
        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Login", "/login", "lock"));
        }});
        request.setAttribute("page", new Page("Login | ".concat(Constanta._APP_NAME)) {{setPath("login");}});

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    protected  void postLogout(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("message", new Message(
                "You have been logged out",
                "See you soon",
                "info",
                "mini",
                "info"));
        response.sendRedirect("/login");
    }

    protected void postLogin(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Employee employee = employeeDAO.loginEmployee(username, password);
        if (employee != null) {
            request.getSession().setAttribute("user", employee);
            response.sendRedirect("/");
        } else {
            request.setAttribute("message", new Message(
                    "Sorry, your credentials doesn't match or exist",
                    "Please check the credentials",
                    "error",
                    "mini",
                    "warning"));
            getLogin(request, response);
        }
    }
}
