<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="course" value="${requestScope.course}"/>
<c:set var="employees" value="${requestScope.employees}"/>

<c:choose>
    <c:when test="${not empty course}">
        <c:set var="formName" value="Update Course"/>
        <c:set var="formDescription" value="Update Course Formulir"/>
        <c:set var="formMethod" value="POST"/>
        <c:set var="formAction" value="/course/update"/>
        <c:set var="formButtonName" value="Update"/>
        <c:set var="formButtonIcon" value="save"/>
    </c:when>
    <c:otherwise>
        <c:set var="formName" value="Create Course"/>
        <c:set var="formDescription" value="Create Course Formulir"/>
        <c:set var="formMethod" value="POST"/>
        <c:set var="formAction" value="/course/create"/>
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
            <input value="${not empty course ? course.id : null}" name="course_id" type="hidden">
            <h4 class="ui dividing header">Course Information</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Course Name</label>
                        <input
                            value="${not empty course ? course.name : null}"
                            name="course_name"
                            placeholder="Course Name"
                            type="text">
                    </div>
                    <div class="field">
                        <label>Course Code</label>
                        <input
                            value="${not empty course ? course.code : null}"
                            name="course_code"
                            placeholder="Course Code"
                            type="text">
                    </div>
                </div>
                <div class="fields">
                    <div class="ten wide field">
                        <label>Course Description</label>
                        <input
                            value="${not empty course ? course.description : null}"
                            name="course_description"
                            placeholder="Course Description"
                            type="text">
                    </div>
                    <div class="six wide field">
                        <label>Course Type</label>
                        <div class="ui fluid search selection dropdown">
                            <input name="course_type" type="hidden" value="${not empty course ? course.type : null}">
                            <i class="dropdown icon"></i>
                            <div class="default text">Course Type</div>
                            <div class="menu">
                                <div class="item" data-value="FIXED">
                                    Fixed
                                </div>
                                <div class="item" data-value="PERIODIC">
                                    Periodic
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Course Details</h4>
            <div class="ui segment basic">
                <div class="two fields">
                    <div class="field">
                        <label>Start Date</label>
                        <div class="ui calendar course start">
                            <div class="ui input left icon">
                                <i class="calendar icon"></i>
                                <input
                                    name="course_startDate"
                                    type="text"
                                    placeholder="Start Date"
                                    value="${not empty course ? course.startDate : null}">
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label>End Date</label>
                        <div class="ui calendar course end">
                            <div class="ui input left icon">
                                <i class="calendar icon"></i>
                                <input
                                    name="course_endDate"
                                    type="text"
                                    placeholder="End Date"
                                    value="${not empty course ? course.endDate : null}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="fields">
                    <div class="sixteen wide field">
                        <label>Place</label>
                        <input
                            value="${not empty course ? course.place : null}"
                            name="course_place"
                            placeholder="Place"
                            type="text">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Trainer/Speaker</h4>
            <div class="ui segment basic">
                <div class="fields">
                    <div class="sixteen wide field">
                        <label>Course Trainer/Speaker</label>
                        <div class="ui fluid search selection dropdown">
                            <input name="course_by" type="hidden" value="${not empty course ? course.courseBy.id : null}">
                            <i class="dropdown icon"></i>
                            <div class="default text">Course Trainer/Speaker</div>
                            <div class="menu">
                                <c:forEach items="${employees}" var="employee">
                                    <div class="item" data-value="${employee.id}">
                                        <div class="ui small header">
                                                ${employee.name}
                                            <div class="sub header">
                                                    ${employee.grade} - ${employee.stream}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
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