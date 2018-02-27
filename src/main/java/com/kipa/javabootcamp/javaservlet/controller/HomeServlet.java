package com.kipa.javabootcamp.javaservlet.controller;

import com.kipa.javabootcamp.javaservlet.common.*;
import com.kipa.javabootcamp.javaservlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class HomeServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        getHome(request, response);
    }

    private void getHome(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("breadcrumbs", new ArrayList<Breadcrumb>() {{
            add(new Breadcrumb("Home", "/", "home"));
        }});
        request.setAttribute("page", new Page("Home | ".concat(Constanta._APP_NAME)) {{setPath("home");}});
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            request.setAttribute("message", new Message(
                "Welcome, "+ ((Employee)session.getAttribute("user")).getName(),
                "Everything is for you",
                "info",
                "mini",
                "certificate loading") {{setCloseable(false);}});
        }
        forward(request, response);
    }
}
