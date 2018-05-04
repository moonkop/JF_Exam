<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>个人信息 - 修改密码</title>
    <%@include file="/WEB-INF/components/header.jsp" %>
    <script src="/dist/js/bootstrapValidator.js"></script>
    <script src="/dist/js/md5.js"></script>
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
                    个人信息 - 修改密码
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/teacher/edit.do">

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
                                        <input type="submit" class="btn btn-primary" value="提交">
                                        <script>
                                            $("form").on("submit", function () {

                                                return false;
                                            })
                                            $("form").bootstrapValidator({
                                                message: 'This value is not valid',
                                                feedbackIcons: {
                                                    valid: 'glyphicon glyphicon-ok',
                                                    invalid: 'glyphicon glyphicon-remove',
                                                    validating: 'glyphicon glyphicon-refresh'
                                                },
                                                fields: {
                                                    oldPassword: {
                                                        validators: {
                                                            notEmpty: {
                                                                message: "请输入原始密码"
                                                            }
                                                        }
                                                    },
                                                    newPassword: {
                                                        validators: {
                                                            notEmpty: {
                                                                message: "请输入新密码"
                                                            },
                                                            stringLength: {
                                                                min: 6,
                                                                max: 30,
                                                                message: "密码长度必须在6到30之间"
                                                            },
                                                            regexp: {
                                                                regexp: /^[a-zA-Z0-9_\.]+$/,
                                                                message: "密码只能为数字、字母或下划线"
                                                            },
                                                        }
                                                    },

                                                    ConfirmNewPassword:{
                                                      validators:{
                                                          notEmpty: {
                                                              message: "请输入新密码"
                                                          },
                                                          identical: {
                                                              field: 'newPassword',
                                                              message: '两次输入密码不一致'
                                                          }
                                                      }
                                                    }
                                                }
                                            });
                                        </script>
                                        <a class="btn btn-default" href='/teacher/detail'>取消</a>
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
