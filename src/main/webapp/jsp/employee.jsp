<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ui segments bordernone">
    <div class="ui segment borderradiusless">
        <div class="ui medium header">
            <i class="ui users orange icon"></i>
            <div class="content">
                Employee
                <div class="sub header">Employee Management</div>
            </div>
        </div>
    </div>
    <div class="ui violet segment">
        <div class="ui clearing basic nopadding segment">
            <a href="/employee/create" class="ui right floated orange tiny button">
                <i class="add icon"></i>
                New Employee
            </a>
        </div>
        <table class="ui celled table">
            <thead>
                <tr class="center aligned">
                    <th>ID</th>
                    <th>Detail</th>
                    <th>Contact</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty employees}">
                    <tr>
                        <td colspan="4" class="center aligned" style="font-style: italic">
                            - No data to display -
                        </td>
                    </tr>
                </c:if>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>
                            <div class="ui tiny header">
                                    #${employee.id}
                                <div class="sub header">
                                    ${employee.code}
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="ui tiny header">
                                ${employee.name}
                                <div class="sub header">
                                    ${employee.grade} - ${employee.stream}
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="ui tiny header">
                                    ${employee.phone}
                                <div class="sub header">
                                    ${employee.address}
                                </div>
                            </div>
                        </td>
                        <td class="center aligned">
                            <a data-tooltip="Detail user" href="/employee?id=${employee.id}" class="circular ui basic icon mini button">
                                <i class="icon user"></i>
                            </a>
                            <a data-tooltip="Edit user" href="/employee/update?id=${employee.id}" class="circular ui violet icon mini button">
                                <i class="icon pencil"></i>
                            </a>
                            <button onclick="document.getElementById(${employee.id}+'deleteform').submit()" data-tooltip="Delete user" class="circular ui red icon mini button">
                                <i class="icon trash"></i>
                            </button>
                        </td>
                        <form id="${employee.id}deleteform" action="/employee/delete?id=${employee.id}" method="post"></form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>