<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>学生管理</title>
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
<!-- start navbar.html-->
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
                <i class="fa fa-user fa-fw"></i>徐强<i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">

                <li>
                    <a>
                        <div>培训机构：管理员</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="/dist/pages/manage/me/detail.html">

                    <strong>详细信息</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
    </ul>
    <!-- /.navbar-top-links -->
</nav>

<!-- end navbar.html-->
<!-- start sidebar.html-->
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
                <a href="index_teacher.html"><i class="fa fa-dashboard fa-fw"></i> 欢迎页</a>
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
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>

<!-- end sidebar.html-->
    <div id="page-wrapper">
<!-- start content -->
<script>
    $(document).ready(
        function () {

            $(".js-view").on("click",function () {
                window.location.href="/dist/pages/manage/student/detail.html"
            })
            $(".js-edit").on("click",function () {
                window.location.href="/dist/pages/manage/student/edit.html"
            })
        }
    )
</script>

<div class="row">
    <div class="col-lg-12">
        <h3>
            学生管理
        </h3>
        <div class="table-btns">
            <a class="btn btn-primary" href="add.html"> 添加学生</a>
            <button class="btn btn-default"> 批量导入学生</button>
        </div>


        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>学校</th>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>机蜂夏令营</td>
                <td>215110</td>
                <td>张甜甜</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>机蜂夏令营</td>
                <td>215111</td>
                <td>刘丹丹</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>机蜂夏令营</td>
                <td>215112</td>
                <td>黄婷婷</td>
                <td>清华大学</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            </tbody>
        </table>


    </div>

</div>

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
