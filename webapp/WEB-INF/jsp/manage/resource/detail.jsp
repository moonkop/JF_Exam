<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>资源管理 - 详情</title>
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
            资源管理 - 详细信息
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属学校</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${resource.schoolVo.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">资源</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${resource.name}</p>
                                </div>
                            </div>
                        
                            <div class="col-sm-offset-2">
                                <a class="btn btn-default" href="/manage/resource/edit?id=${resource.id}">编辑</a>
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
<%@include file="/WEB-INF/components/footer.jsp"%>
</body>

</html>
