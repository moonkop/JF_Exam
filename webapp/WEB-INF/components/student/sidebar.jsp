<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-default sidebar app-side" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">


            <c:forEach items="${sessionScope.loginMenu}" var="menu">
                <li>

                    <a href="${menu.url}">${menu.name}</a>
                </li>

                <c:forEach items="${menu.childs}" var="child">
                    <ul class="nav nav-second-level">

                        <li>

                            <<a href="${child.url}">${child.name}</a>
                        </li>
                    </ul>
                </c:forEach>
            </c:forEach>
        </ul>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
