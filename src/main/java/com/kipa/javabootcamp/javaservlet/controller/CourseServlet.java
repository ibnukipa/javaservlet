package com.kipa.javabootcamp.javaservlet.controller;

import com.kipa.javabootcamp.javaservlet.common.Breadcrumb;
import com.kipa.javabootcamp.javaservlet.common.Constanta;
import com.kipa.javabootcamp.javaservlet.common.Page;
import com.kipa.javabootcamp.javaservlet.dao.CourseDao;
import com.kipa.javabootcamp.javaservlet.model.Course;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/course", "/course/create", "/course/update", "/course/delete"})
public class CourseServlet extends AbstractServlet {
    private CourseDao courseDao;

    public void init() {
        courseDao = new CourseDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if(action.equals("/course/create")) {
                getCourse(request, response);
            } else if(action.equals("/course/update")) {
                handleNotFound(request, response);
            } else if(action.equals("/course/delete")) {
                handleNotFound(request, response);
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
            if(action.equals("/course/create")) {
                handleNotFound(request, response);
            } else if(action.equals("/course/update")) {
                handleNotFound(request, response);
            } else if(action.equals("/course/delete")) {
                handleNotFound(request, response);
            } else {
                if(request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) > 0) {
                    getCourse(request, response);
                } else {
                    getCourses(request, response);
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void getCourses(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Course> courses = courseDao.getCourses();

        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("Courses", "/course", "bookmark"));
        }});
        request.setAttribute("page", new Page("Courses | ".concat(Constanta._APP_NAME)) {{setPath("course");}});
        request.setAttribute("courses", courses);

        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void getCourse(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer courseId = Integer.parseInt(request.getParameter("id"));
        final Course course = courseDao.getCourseById(courseId);
        if(course == null) {
            handleNotFound(request, response);
        } else {
            request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>(){{
                add(new Breadcrumb("Home", "/", "home"));
                add(new Breadcrumb("Courses", "/course", "bookmark"));
                add(new Breadcrumb(course.getName(), null, "star"));
            }});
            request.setAttribute("page", new Page(course.getName().concat(" | ".concat(Constanta._APP_NAME))) {{setPath("course/detail");}});
            request.setAttribute("course", course);

            String path = getTemplatePath("/");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }
}
