<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui segments bordernone">
    <div class="ui segment borderradiusless">
        <div class="ui medium header">
            <i class="ui bookmark orange icon"></i>
            <div class="content">
                Course
                <div class="sub header">Course Management</div>
            </div>
        </div>
    </div>
    <div class="ui violet segment">
        <div class="ui clearing basic nopadding segment">
            <a href="/course/create" class="ui right floated orange tiny button">
                <i class="add icon"></i>
                New Course
            </a>
        </div>
        <table class="ui celled table">
            <thead>
            <tr class="center aligned">
                <th>ID</th>
                <th>Detail</th>
                <th>Trainer</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty courses}">
                <tr>
                    <td colspan="4" class="center aligned" style="font-style: italic">
                        - No data to display-
                    </td>
                </tr>
            </c:if>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>
                        <div class="ui tiny header">
                            #${course.id}
                            <div class="sub header">
                                ${course.code}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="ui tiny header">
                            ${course.name}
                            <div class="sub header">
                                <fmt:formatDate value="${course.startDate}" pattern="dd, MMM YYYY"/> -
                                <fmt:formatDate value="${course.endDate}" pattern="dd, MMM YYYY"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="ui tiny header">
                            ${course.courseBy.name}
                            <div class="sub header">
                                at ${course.place}
                            </div>
                        </div>
                    </td>
                    <td class="center aligned">
                        <a data-tooltip="Detail course" href="/course?id=${course.id}" class="circular ui basic icon mini button">
                            <i class="icon user"></i>
                        </a>
                        <a data-tooltip="Edit course" href="/course/update?id=${course.id}" class="circular ui violet icon mini button">
                            <i class="icon pencil"></i>
                        </a>
                        <button onclick="document.getElementById(${course.id}+'deleteform').submit()" data-tooltip="Delete course" class="circular ui red icon mini button">
                            <i class="icon trash"></i>
                        </button>
                    </td>
                    <form id="${course.id}deleteform" action="/course/delete?id=${course.id}" method="post"></form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>