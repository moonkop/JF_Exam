<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>


<script type="text/javascript">
    $(document).ready(function () {
        $("#reset").on("click",function () {

          // var password=$("#idCardNo").contents().text();
          var id=$("#id").val();
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/teacher/manage/resetPassword.do",
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
            教师管理 - 详细信息
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">账号</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static" id="teacherId">${teacher.teacherId}</p>
                                    <input style="display: none" id="id" value="${teacher.id}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${teacher.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static"> ${teacher.gender == 1 ?"男":"女"}   </p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${teacher.role.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static" id="idCardNo">${teacher.idCardNo}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${teacher.mail}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${teacher.telephone}</p>
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <a class="btn btn-default" href="/teacher/manage/edit?id=${teacher.id}">编辑</a>
                                <button class="btn btn-default" id="reset">重置密码</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- end content -->
