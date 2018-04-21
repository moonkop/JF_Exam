<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>个人信息 - 编辑</title>
    <%@include file="/WEB-INF/components/header.jsp"%>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp"%>
    <%@include file="/WEB-INF/components/sidebar.jsp"%>

    <div id="page-wrapper">
<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            个人信息 - 编辑
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">徐强</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">超级管理员</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">320502199705152254</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail"
                                           placeholder="请输入Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTel" class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputTel"
                                           placeholder="请输入电话号码">
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <button type="submit" class="btn btn-default">取消</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>

    </div>

</div>


<!-- end content -->
    </div>
</div>
<%@include file="/WEB-INF/components/footer.jsp"%>
</body>

</html>
