<%@ page import="com.njmsita.exam.authentic.model.TeacherVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">机蜂 - 在线考试系统</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-user fa-fw"></i>${loginUser.name}<i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">

                <li>
                    <a>
                        <div>身份：${loginUser.role.name}</div>
                    </a>
                </li>
                <script>
                    var User={
                        id:'${loginUser.id}',
                        name:'${loginUser.name}',
                        role:'${loginUser.role.id}'
                    }
                    if(User.id=="")
                    {
                        window.location.href='/teacher/login'
                    }
                </script>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/teacher/detail">

                    <strong>详细信息</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/teacher/logout">
                        <strong>退出登录</strong>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
    </ul>
    <!-- /.navbar-top-links -->
</nav>
