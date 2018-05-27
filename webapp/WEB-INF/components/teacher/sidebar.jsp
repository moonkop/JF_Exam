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

<%--<li>--%>
<%--<a href="/teacher/welcome"><i class="fa fa-dashboard fa-fw"></i> 欢迎页</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/paper"><i class="fa fa-bar-chart-o fa-fw"></i> 我的试卷</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/exam/manage"><i class="fa fa-table fa-fw"></i> 我的考试</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#"><i class="fa fa-edit fa-fw"></i> 批改</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#"><i class="fa fa-sitemap fa-fw"></i> 系统管理<span class="fa arrow"></span></a>--%>
<%--<ul class="nav nav-second-level">--%>
<%--<li>--%>
<%--<a href="/teacher/manage">教师管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/school">学校管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/classroom">班级管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/student/manage">学生管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/role">角色管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/resource">资源管理</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<!-- /.nav-second-level -->--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#"><i class="fa fa-sitemap fa-fw"></i> 题库管理<span class="fa arrow"></span></a>--%>
<%--<ul class="nav nav-second-level">--%>
<%--<li>--%>
<%--<a href="/manage/bank/subject">科目管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/bank/topic">知识点管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/bank/question">题目管理</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="/manage/bank/questionType">题目类型管理</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<!-- /.nav-second-level -->--%>
<%--</li>--%>
<%--</ul>--%>

