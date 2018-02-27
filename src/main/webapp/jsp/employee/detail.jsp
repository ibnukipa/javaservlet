<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                                    by ${course.courseBy.name}
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
    </div>
</div>