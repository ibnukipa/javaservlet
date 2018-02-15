<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty message}">
    <div class="ui icon ${message.size} ${message.type} message borderradiusless">
        <i class="${message.icon} icon"></i>
        <c:if test="${message.closeable}">
            <i class="close icon"></i>
        </c:if>
        <div class="content">
            <div class="header">
                ${message.title}
            </div>
            <p>${message.description}</p>
        </div>
    </div>
</c:if>
