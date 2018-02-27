<%@ page import="com.kipa.javabootcamp.javaservlet.model.Employee" %>
<%@ page import="com.kipa.javabootcamp.javaservlet.model.Course" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var='employee' value='<%=(Employee) request.getAttribute("employee")%>'/>
<c:set var='courses' value='<%=(List<Course>) request.getAttribute("courses")%>'/>
<c:set var='classes' value='<%=(List<Course>) request.getAttribute("classes")%>'/>

<div class="ui segments bordernone">
    <div class="ui clearing segment borderradiusless">
        <div class="ui small header left floated aligned nomargin">
            <i class="bordered user orange icon"></i>
            <div class="content">
                ${employee.name}
                <div class="sub header">${employee.grade} - ${employee.stream}</div>
            </div>
        </div>
        <div class="ui small header right floated aligned">
            <div class="content">
                ${employee.phone}
                <div class="sub header">${employee.address}</div>
            </div>
            <i class="bordered call orange icon"></i>
        </div>
    </div>
    <div class="ui violet segment">
        <table class="ui celled striped table">
            <thead>
                <tr>
                    <th colspan="2">
                        <i class="bookmark icon"></i>
                        Courses
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty courses}">
                    <tr>
                        <td colspan="2" class="center aligned" style="font-style: italic">
                            - No data to display -
                        </td>
                    </tr>
                </c:if>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>
                            <div class="ui tiny header">
                                ${course.name}
                                <div class="sub header">
                                    by ${(course.getCourseBy()).name}
                                </div>
                            </div>
                        </td>
                        <td class="center aligned collapsing">
                            <div class="ui tiny header">
                                <c:choose>
                                    <c:when test="${course.type eq 'FIXED'}">
                                        <fmt:formatDate pattern = "MMM, dd YYYY" value = "${course.startDate}" />
                                        -
                                        <fmt:formatDate pattern = "MMM, dd YYYY" value = "${course.endDate}" />
                                        <div class="sub header">
                                            <fmt:formatDate pattern = "HH:mm a" value = "${course.startDate}" />
                                            -
                                            <fmt:formatDate pattern = "HH:mm a" value = "${course.endDate}" />
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate pattern = "HH:mm a" value = "${course.startDate}" />
                                        -
                                        <fmt:formatDate pattern = "HH:mm a" value = "${course.endDate}" />
                                        <div class="sub header">
                                            every <fmt:formatDate pattern = "EEEE" value = "${course.startDate}" />
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <table class="ui celled striped table">
            <thead>
            <tr>
                <th colspan="2">
                    <i class="bookmark icon"></i>
                    Classes
                </th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty classes}">
                <tr>
                    <td colspan="2" class="center aligned" style="font-style: italic">
                        - No data to display -
                    </td>
                </tr>
            </c:if>
            <c:forEach var="courseBy" items="${classes}">
                <tr>
                    <td>
                        <div class="ui tiny header">
                                ${courseBy.name}
                            <div class="sub header">
                                ${fn:length(courseBy.getParticipants())} participants
                            </div>
                        </div>
                    </td>
                    <td class="center aligned collapsing">
                        <div class="ui tiny header">
                            <c:choose>
                                <c:when test="${courseBy.type eq 'FIXED'}">
                                    <fmt:formatDate pattern = "MMM, dd YYYY" value = "${courseBy.startDate}" />
                                    -
                                    <fmt:formatDate pattern = "MMM, dd YYYY" value = "${courseBy.endDate}" />
                                    <div class="sub header">
                                        <fmt:formatDate pattern = "HH:mm a" value = "${courseBy.startDate}" />
                                        -
                                        <fmt:formatDate pattern = "HH:mm a" value = "${courseBy.endDate}" />
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatDate pattern = "HH:mm a" value = "${courseBy.startDate}" />
                                    -
                                    <fmt:formatDate pattern = "HH:mm a" value = "${courseBy.endDate}" />
                                    <div class="sub header">
                                        every <fmt:formatDate pattern = "EEEE" value = "${courseBy.startDate}" />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>