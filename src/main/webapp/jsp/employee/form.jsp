<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="employee" value="${requestScope.employee}"/>

<c:choose>
    <c:when test="${not empty employee}">
        <c:set var="formName" value="Update Employee"/>
        <c:set var="formDescription" value="Update Employee Formulir"/>
        <c:set var="formMethod" value="POST"/>
        <c:set var="formAction" value="/employee/update"/>
        <c:set var="formButtonName" value="Update"/>
        <c:set var="formButtonIcon" value="save"/>
    </c:when>
    <c:otherwise>
        <c:set var="formName" value="Create Employee"/>
        <c:set var="formDescription" value="Create Employee Formulir"/>
        <c:set var="formMethod" value="POST"/>
        <c:set var="formAction" value="/employee/create"/>
        <c:set var="formButtonName" value="Create"/>
        <c:set var="formButtonIcon" value="save"/>
    </c:otherwise>
</c:choose>

<div class="ui segments bordernone">
    <div class="ui segment borderradiusless">
        <div class="ui medium header">
            <i class="bordered orange file text icon"></i>
            <div class="content">
                ${formName}
                <div class="sub header">${formDescription}</div>
            </div>
        </div>
    </div>
    <div class="ui violet segment">
        <form class="ui form" method="${formMethod}" action="${formAction}">
            <input value="${not empty employee ? employee.id : null}" name="employee_id" type="hidden">
            <h4 class="ui dividing header">Person Information</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Full Name</label>
                        <input value="${not empty employee ? employee.name : null}" name="employee_name" placeholder="Full Name" type="text">
                    </div>
                    <div class="field">
                        <label>Employee Code</label>
                        <input value="${not empty employee ? employee.code : null}" name="employee_code" placeholder="Employee Code" type="text">
                    </div>
                </div>
                <div class="fields">
                    <div class="ten wide field">
                        <label>Address</label>
                        <input value="${not empty employee ? employee.address : null}" name="employee_address" placeholder="Address" type="text">
                    </div>
                    <div class="six wide field">
                        <label>Phone Number</label>
                        <input value="${not empty employee ? employee.phone : null}" name="employee_phone" placeholder="Phone Number" type="text">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Account</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Username</label>
                        <input value="${not empty employee ? employee.username : null}" name="employee_username" placeholder="Username" type="text">
                    </div>
                    <div class="field">
                        <label>Password</label>
                        <input value="${not empty employee ? employee.password : null}" name="employee_password" placeholder="Password" type="password">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Posistion</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Grade</label>
                        <input value="${not empty employee ? employee.grade : null}" name="employee_grade" placeholder="Grade" type="text">
                    </div>
                    <div class="field">
                        <label>Stream</label>
                        <input value="${not empty employee ? employee.stream : null}" name="employee_stream" placeholder="Stream" type="text">
                    </div>
                </div>
            </div>
            <div class="ui hidden divider"></div>
            <div class="ui buttons">
                <button class="ui orange button">
                    <i class="icon ${formButtonIcon}"></i>
                    ${formButtonName}
                </button>
                <div class="or"></div>
                <a href="/employee" class="ui button">Cancel</a>
            </div>
        </form>
    </div>
</div>