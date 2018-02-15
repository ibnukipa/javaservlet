<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.kipa.javabootcamp.javaservlet.common.Constanta" %>

<div class="ui top fixed menu kipa">
    <div class="ui huge violet header middle">
        <i class="circular inverted violet diamond icon"></i>
        <div class="content">
            ${ Constanta._APP_NAME }
            <div class="sub header">${ Constanta._APP_DESCRIPTION }</div>
        </div>
    </div>
    <c:if test="${not empty sessionScope.user}">
        <div class="ui inverted violet menu lowest borderradiusless">
            <a href="/announcement" class="item${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'announcement') ? " active" : ""}">
                Announcements
            </a>
            <a href="/employee" class="item${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'employee') ? " active" : ""}">
                Employees
            </a>
            <a href="/project" class="item${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'project') ? " active" : ""}">
                Projects
            </a>
            <a href="/course" class="item${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'course') ? " active" : ""}">
                Courses
            </a>
        </div>
    </c:if>
</div>