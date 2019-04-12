<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- start content -->
<script>
    MyAjaxForm("form");
</script>
<div class="row">
    <div class="col-lg-12">
        <h3>
            学校管理
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal" action="/manage/school/edit.do">
                            <div class="form-group" style="display:none">
                                <label class="col-sm-2 control-label">学校id</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-id" name="id" value="${school.id}"
                                           placeholder="请输入学校名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">学校名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-name" name="name"
                                           value="${school.name}"
                                           placeholder="请输入学校名">
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <a class="btn btn-default " onclick="window.location.href='/manage/school'">取消</a>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>
<!-- end content -->
