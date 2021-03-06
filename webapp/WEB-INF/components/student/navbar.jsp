<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="/student/login">机蜂 - 在线考试系统</a>
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
                        window.location.href='/student/login'
                    }

                </script>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/student/detail">

                    <strong>详细信息</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/student/logout">
                        <strong>退出登录</strong>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
    </ul>
    <!-- /.navbar-top-links -->
</nav>
