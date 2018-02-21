package com.kipa.javabootcamp.javaservlet.controller;

import com.kipa.javabootcamp.javaservlet.common.Breadcrumb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String VIEW_PREFIX = "/jsp";
    private static final String VIEW_SUFFIX = ".jsp";

    static String getTemplatePath(String path)
    {
        if (path.equalsIgnoreCase("/"))
        {
            return path + "index" + VIEW_SUFFIX;
        }
        else
        {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

    static void handleNotFound(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
            add(new Breadcrumb("Home", "/", "home"));
            add(new Breadcrumb("404 - Not Found", "/notfound", "warning sign"));
        }});
        forward(request, response);
    }

    static void forward(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String path = getTemplatePath("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
