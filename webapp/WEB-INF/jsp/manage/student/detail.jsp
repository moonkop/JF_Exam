<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(document).ready(function () {
        $("#resetpassword").on("click",function () {

            // var password=$("#idCardNo").contents().text();
            var id=${student.id};
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/student/manage/resetPassword.do",
                data: {
                    // password: password,
                    id:id
                },
                success: function (result) {
                    if (result.code == 100)
                    {
                        alert("重置成功")
                    }
                    else
                    {
                        alert(result.message);
                    }
                }

            })
        })
    })
</script>


<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            学生管理 - 详细信息
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学校</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.school.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">班级</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.classroom.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学号</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.studentId}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.role.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.idCardNo}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.mail}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${student.telephone}</p>
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <a class="btn btn-default" href="/student/manage/edit?id=${student.id}">编辑</a>
                                <button class="btn btn-default" id="resetpassword">重置密码</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- end content -->
