<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-default sidebar app-side" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <c:forEach items="${loginMenu}" var="menu">
                <c:if test="${not empty menu.url}">
                    <li>
                        <a href="${menu.url}"><i class="fa ${menu.icon}"></i>${menu.name}</a>
                    </li>
                </c:if>
                <c:if test="${empty menu.url}">
                    <li>
                        <a href="#"><i class="fa ${menu.icon}"></i>${menu.name}</a>
                        <ul class="nav nav-second-level">
                            <c:forEach items="${menu.childs}" var="child">
                                <li>
                                    <a href="${child.url}"><i class="fa ${child.icon}"></i>${child.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
