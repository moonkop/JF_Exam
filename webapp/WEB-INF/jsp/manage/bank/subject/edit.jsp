<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script>
    $(document).ready(function () {
        $("#cancel").on("click",function () {
            window.history.go(-1);
        })

    })
</script>
        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    科目管理 - 编辑
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/manage/bank/subject/edit.do">
                                    <input type="text" class="form-control" style="display: none;" name="id"
                                           value="${subject.id}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">科目id</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${subject.id}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-name">科目名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-name" name="name"
                                                   value="${subject.name}"
                                                   placeholder="请输入科目名">
                                        </div>
                                    </div>
                                    <div class="col-sm-offset-2">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button class="btn btn-default js-cancel " id="cancel">取消</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- end content -->
