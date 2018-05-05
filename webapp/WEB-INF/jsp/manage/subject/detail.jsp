<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>科目管理 - 详情</title>
    <%@include file="/WEB-INF/components/header.jsp" %>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp" %>
    <%@include file="/WEB-INF/components/sidebar.jsp" %>

    <div id="page-wrapper">
        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    科目管理 - 详细信息
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">科目id</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${subject.id}</p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">科目名称</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${subject.name}</p>
                                        </div>
                                    </div>

                                    <div class="col-sm-offset-2">
                                        <a class="btn btn-default" href="/bank/manage/subject/edit?id=${subject.id}">编辑</a>
                                    </div>
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
<%@include file="/WEB-INF/components/footer.jsp" %>
</body>

</html>
