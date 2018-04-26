<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>学生管理 - 编辑</title>
    <%@include file="/WEB-INF/components/header.jsp" %>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp" %>
    <%@include file="/WEB-INF/components/sidebar.jsp" %>
    <script src="/vendor/jstree/jstree.js"></script>
    <link rel="stylesheet" href="/vendor/jstree/themes/default/style.css"/>
    <div id="page-wrapper">
        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    角色管理 - 编辑
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/manage/role/edit.do">
                                    <div class="form-group" style="display:none">
                                        <input type="text" class="form-control" id="id" name="id" style="display: none"
                                               value="${role.id}">
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-name">角色名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-name" name="name"
                                                   value="${role.name}"
                                                   placeholder="请输入角色名">
                                        </div>
                                    </div>

                                    <div id="resource-tree">

                                    </div>


                                    <div class="col-sm-offset-2">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button class="btn btn-default js-cancel">取消</button>
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
<%@include file="/WEB-INF/components/footer.jsp" %>
</body>

</html>
