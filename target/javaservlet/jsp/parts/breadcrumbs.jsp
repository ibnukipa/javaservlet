<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ui ${param.position} nomargin segment breadcrumb kipa borderradiusless bordernone">
    <c:forEach var="breadcrumb" items="${breadcrumbs}" varStatus="loop">
        <c:set var="icon" scope="request" value="${breadcrumb.icon != null ? breadcrumb.icon : 'start'}"/>
        <c:choose>
            <c:when test="${fn:length(breadcrumbs) eq (loop.index + 1)}">
                <i class="disabled ${icon} icon"></i>
                <div class="active section">${breadcrumb.name}</div>
            </c:when>
            <c:when test="${fn:length(breadcrumbs) - 1 eq (loop.index + 1)}">
                <i class="disabled ${icon} icon"></i>
                <a href="${breadcrumb.url}" class="section">${breadcrumb.name}</a>
                <i class="right arrow icon divider"></i>
            </c:when>
            <c:otherwise>
                <i class="disabled ${icon} icon"></i>
                <a href="${breadcrumb.url}" class="section">${breadcrumb.name}</a>
                <i class="right chevron icon divider"></i>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>