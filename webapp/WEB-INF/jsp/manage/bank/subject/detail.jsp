<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

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
                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label">科目id</label>--%>
                            <%--<div class="col-sm-8">--%>
                            <%--<p class="form-control-static">${subject.id}</p>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <input type="text" class="form-control" style="display: none;" name="id"
                                   value="${subject.id}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">科目名称</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${subject.name}</p>
                                </div>
                            </div>

                            <div class="col-sm-offset-2">
                                <a class="btn btn-default" href="/manage/bank/subject/edit?id=${subject.id}">编辑</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- end content -->
