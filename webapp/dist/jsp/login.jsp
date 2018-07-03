<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">

<head>
    <title>SB Admin 2 - Bootstrap Admin Theme</title>
<!-- start header.html-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="/vendor/morrisjs/morris.css" rel="stylesheet">
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/vendor/bootstrap-table/bootstrap-table.css">
<script src="/vendor/jquery/jquery.min.js"></script>

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- end header.html-->
    <script type="text/javascript">
        $(function () {
            $("#commit").click(function () {
                $("form:first").submit();
            })
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row login-title">
        机锋在线考试系统
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">学生登录</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="${pageContext.request.contextPath}/stu/login" method="post">
                        <fieldset>

                            <div class="form-group">
                                <select class="form-control" placeholder="学号" name="email" type="email" autofocus>
                                    <option>
                                        机蜂夏令营
                                    </option>
                                    <option>
                                        清华大学
                                    </option>
                                </select>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="学号" name="email" type="studentId" value="123" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" name="password" type="password" value="123">
                            </div>
                            <div class="checkbox text-right">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">记住我
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="javascript:void(0)" id="commit" class="btn btn-lg btn-success btn-block">登录</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="position: fixed;bottom:0px;right: 0px;">
    <a href="/teacher/login" id="teaLog"> 教师登录</a>
</div>
<!-- start footer.html-->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendor/metisMenu/metisMenu.min.js"></script>
<script src="/dist/js/sb-admin-2.js"></script>
<!-- end footer.html-->


</body>

</html>
