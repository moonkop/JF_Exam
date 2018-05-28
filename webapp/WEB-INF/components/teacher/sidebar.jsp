<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="navbar-default sidebar app-side" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <c:forEach items="${loginMenu}" var="menu">
                <li>
                    <c:if test="${not empty menu.url}">
                        <a href="${menu.url}">${menu.name}</a>
                    </c:if>
                    <c:if test="${empty menu.url}">
                        <a href="#">${menu.name}</a>

                    </c:if>
                    <c:if test="${not empty menu.childs}">
                    <ul class="nav nav-second-level">

                            <c:forEach items="${menu.childs}" var="child">

                                <li>
                                    <a href="${child.url}"> ${child.name}</a>
                                </li>

                            </c:forEach>
                    </ul>

                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>

