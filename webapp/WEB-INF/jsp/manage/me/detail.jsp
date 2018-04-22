
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>



<%@ page import="com.njmsita.exam.utils.consts.SysConsts" %>
<%! String key=SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME;
%>
<html lang="en">

<head>
    <title>个人信息</title>
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
            个人信息
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginTeacher.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginTeacher.troleVo.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginTeacher.idCardNo}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginTeacher.mail}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginTeacher.telephone}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-offset-2">
                            <button class="btn btn-default" onclick="window.location.href= '/teacher/edit'">编辑</button>
                        </div>

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
