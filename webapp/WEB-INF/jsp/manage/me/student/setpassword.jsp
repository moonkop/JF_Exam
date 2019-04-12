<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            个人信息 - 修改密码
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">

                            <div class="form-group">
                                <label for="input-old-password" class="col-sm-2 control-label">原始密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="input-old-password"
                                           name="oldPassword" value=""
                                           placeholder="请输入原始密码">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="input-old-password" class="col-sm-2 control-label">新密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="input-new-password"
                                           name="newPassword" value=""
                                           placeholder="请输入新密码">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="input-old-password" class="col-sm-2 control-label">确认密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="input-repeat-new-password"
                                           name="ConfirmNewPassword" value=""
                                           placeholder="请再次输入新密码">
                                </div>
                            </div>


                            <div class="col-sm-offset-2">
                                <button  id="submit" class="btn btn-primary">提交</button>
                                <script>
                                    $("#submit").on("click",function (e) {
                                        e.preventDefault();
                                        myajax({
                                            type: "post",
                                            dataType: "json",
                                            url: "/student/setpassword.do",
                                            data: {
                                                oldPassword:$("#input-old-password").val(),
                                                newPassword:$("#input-new-password").val(),
                                                repeatpassword:$("#input-repeat-new-password").val(),
                                            },
                                            success: function (res) {
                                                alert("修改成功");
                                                window.location.href='/student/logout';
                                            },
                                            error: function (res) {
                                                alert("修改失败,原始密码错误或者两次密码不一致");
                                            }
                                        })
                                    })
                                </script>
                                <a class="btn btn-default" href='/student/detail'>取消</a>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    </div>

</div>


<!-- end content -->
