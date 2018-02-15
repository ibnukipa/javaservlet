<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ui segments bordernone">
    <div class="ui segment borderradiusless">
        <div class="ui medium header">
            <i class="bordered orange pencil icon"></i>
            <div class="content">
                Update Employee
                <div class="sub header">Update Employee Formulir</div>
            </div>
        </div>
    </div>
    <div class="ui violet segment">
        <form class="ui form" method="post" action="/employee/update">
            <input value="${employee.id}" name="employee_id" type="hidden">
            <h4 class="ui dividing header">Person Information</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Full Name</label>
                        <input value="${employee.name}" name="employee_name" placeholder="Full Name" type="text">
                    </div>
                    <div class="field">
                        <label>Employee Code</label>
                        <input value="${employee.code}" name="employee_code" placeholder="Employee Code" type="text">
                    </div>
                </div>
                <div class="fields">
                    <div class="ten wide field">
                        <label>Address</label>
                        <input value="${employee.address}" name="employee_address" placeholder="Address" type="text">
                    </div>
                    <div class="six wide field">
                        <label>Phone Number</label>
                        <input value="${employee.phone}" name="employee_phone" placeholder="Phone Number" type="text">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Account</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Username</label>
                        <input value="${employee.username}" name="employee_username" placeholder="Username" type="text">
                    </div>
                    <div class="field">
                        <label>Password</label>
                        <input value="${employee.password}" name="employee_password" placeholder="Password" type="password">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Posistion</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Grade</label>
                        <input value="${employee.grade}" name="employee_grade" placeholder="Grade" type="text">
                    </div>
                    <div class="field">
                        <label>Stream</label>
                        <input value="${employee.stream}" name="employee_stream" placeholder="Stream" type="text">
                    </div>
                </div>
            </div>
            <div class="ui hidden divider"></div>
            <div class="ui buttons">
                <button class="ui orange button">
                    <i class="icon save"></i>
                    Update
                </button>
                <div class="or"></div>
                <a href="/employee" class="ui button">Cancel</a>
            </div>
        </form>
    </div>
</div>