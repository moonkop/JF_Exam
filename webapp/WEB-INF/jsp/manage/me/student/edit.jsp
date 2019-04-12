<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

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
                        <form role="form" class="form-horizontal" action="/student/edit.do">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginUser.name}</p>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginUser.role.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${loginUser.idCardNo}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail" name="mail" value="${loginUser.mail}"
                                           placeholder="请输入Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTel" class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputTel" name="telephone" value="${loginUser.telephone}"
                                           placeholder="请输入电话号码">
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <input type="submit" class="btn btn-primary" value="提交">

                                <button class="btn btn-default" onclick="window.location.href='/student/detail'" >取消</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

<script>
    MyAjaxForm("form");
</script>
<!-- end content -->
