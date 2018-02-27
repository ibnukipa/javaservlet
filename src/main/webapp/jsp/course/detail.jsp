<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui segments bordernone">
    <div class="ui clearing segment borderradiusless">
        <div class="ui small header left floated aligned nomargin">
            <i class="bordered bookmark orange icon"></i>
            <div class="content">
                ${course.name}
                <div class="sub header">by ${course.courseBy.name}</div>
            </div>
        </div>
        <div class="ui small header right floated aligned">
            <div class="content">
                ${course.type}
                <div class="sub header">
                    <fmt:formatDate pattern = "MMM, dd YYYY" value = "${course.startDate}" />
                    -
                    <fmt:formatDate pattern = "MMM, dd YYYY" value = "${course.endDate}" />
                </div>
            </div>
            <i class="bordered star orange icon"></i>
        </div>
    </div>
    <div class="ui violet segment">
        <table class="ui celled striped table">
            <thead>
            <tr>
                <th colspan="2">
                    <i class="users icon"></i>
                    Participants
                </th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty course.participants}">
                <tr>
                    <td colspan="2" class="center aligned" style="font-style: italic">
                        - No data to display -
                    </td>
                </tr>
            </c:if>
            <c:forEach var="participant" items="${course.participants}">
                <tr>
                    <td>
                        <div class="ui tiny header">
                                ${participant.name}
                            <div class="sub header">
                                ${participant.grade} - ${participant.stream}
                            </div>
                        </div>
                    </td>
                    <td class="center aligned collapsing">
                        <button onclick="document.getElementById(${participant.id}+'disenrollform').submit()" data-tooltip="Remove participant" class="circular ui red icon mini button">
                            <i class="icon trash"></i>
                        </button>
                    </td>
                    <form id="${participant.id}disenrollform" action="/course/enrollment?enroll_type=removeparticipant&employee_id=${participant.id}&course_id=${course.id}&id=${course.id}" method="post"></form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>