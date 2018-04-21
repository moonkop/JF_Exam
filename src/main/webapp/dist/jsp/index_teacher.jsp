<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title><!-- @@var title --></title>
<!-- start header.html-->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="/vendor/morrisjs/morris.css" rel="stylesheet">
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="/vendor/jquery/jquery.min.js"></script>

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- end header.html-->
</head>

<body>

<div id="wrapper">
<!-- start navbar.jsp-->
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
                <i class="fa fa-user fa-fw"></i>{something form java}<i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">

                <li>
                    <a>
                        <div>{something form java}</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/dist/pages/manage/me/detail.html">

                    <strong>{something form java}</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
    </ul>
    <!-- /.navbar-top-links -->
</nav>

<!-- end navbar.jsp-->
<!-- start sidebar.jsp-->
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <!--<li class="sidebar-search">-->
            <!--<div class="input-group custom-search-form">-->
            <!--<input type="text" class="form-control" placeholder="Search...">-->
            <!--<span class="input-group-btn">-->
            <!--<button class="btn btn-default" type="button">-->
            <!--<i class="fa fa-search"></i>-->
            <!--</button>-->
            <!--</span>-->
            <!--</div>-->
            <!--&lt;!&ndash; /input-group &ndash;&gt;-->
            <!--</li>-->
            <li>
                <a href="index_teacher.html"><i class="fa fa-dashboard fa-fw"></i>{something form java</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 组卷</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-table fa-fw"></i> 发起考试</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-edit fa-fw"></i> 查看题库</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 查看分数</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-edit fa-fw"></i> 批改</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-sitemap fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/dist/pages/manage/teacher/list.html">教师管理</a>
                    </li>
                    <li>
                        <a href="/dist/pages/manage/student/list.html">学生管理</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#"><i class="fa fa-sitemap fa-fw"></i> 题库管理<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/dist/pages/manage/subject/list.html">科目管理</a>
                    </li>
                    <li>
                        <a href="/dist/pages/manage/topic/list.html">知识点管理</a>
                    </li>
                    <li>
                        <a href="/dist/pages/manage/question/list.html">题目管理</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>

<!-- end sidebar.jsp-->
    <div id="page-wrapper">
<!-- start content -->


<!-- end content -->
    </div>
</div>
<!-- start footer.html-->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendor/metisMenu/metisMenu.min.js"></script>
<script src="/dist/js/sb-admin-2.js"></script>
<!-- end footer.html-->
</body>

</html>
