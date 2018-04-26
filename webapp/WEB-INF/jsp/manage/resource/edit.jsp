<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>资源管理 - 编辑</title>
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
                    资源管理 - 编辑
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/manage/resource/edit.do">
                                    <div class="form-group" style="display:none">
                                        <input type="text" class="form-control" name="id" style="display: none"
                                               value="${resource.id}">
                                        <input type="text" class="form-control" name="parent.id" style="display: none"
                                               value="${parent.id}">
                                    </div>




                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">父资源</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${parent.name}</p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="selectSchool" class="col-sm-2 control-label">资源类型</label>
                                        <div class="col-sm-8">
                                            <select name="resourcetype.id" id="selectSchool" class="form-control">
                                                <c:forEach items="${requestScope.types}" var="type">
                                                    <option value="${type.id}"
                                                            <c:if test="${resource.resourcetype==type}">selected</c:if>
                                                    >
                                                            ${type.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-name">资源名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-name" name="name"
                                                   value="${resource.name}"
                                                   placeholder="请输入资源名">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-url">资源路径</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-url" name="url"
                                                   value="${resource.url}"
                                                   placeholder="请输入资源路径">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-seq">资源序号</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-seq" name="seq"
                                                   value="${resource.seq}"
                                                   placeholder="请输入资源序号">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-remark">备注</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-remark" name="remark"
                                                   value="${resource.remark}"
                                                   placeholder="请输入备注">
                                        </div>
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
