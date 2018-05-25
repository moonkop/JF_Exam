<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    学生管理 - 添加
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/student/manage/edit.do"
                                      method="post">

                                    <input type="text" class="form-control" id="id" name="id" style="display: none"
                                           value="${student.id}">

                                    <script>
                                        $(document).ready(function () {
                                            function refreshClassroomSelect()
                                            {
                                                $.ajax(
                                                    {
                                                        url: "/manage/getClassroomBySchoolID.do",
                                                        data: {
                                                            id: $("#selectSchool").val()
                                                        },
                                                        success: function (result) {
                                                            $("#selectClassroom").empty();
                                                            $.each(result, function (key, val) {
                                                                $("#selectClassroom").append(
                                                                    '<option value=' + val.id + '>' + val.name + '</option>'
                                                            )
                                                            });
                                                        }
                                                    }
                                                )
                                            }
                                            refreshClassroomSelect();
                                            $("#selectSchool").on("change",function()
                                            {
                                                refreshClassroomSelect();
                                            })
                                        })
                                    </script>

                                    <div class="form-group">
                                        <label for="selectClassroom" class="col-sm-2 control-label">班级</label>

                                        <div class="col-sm-8">
                                            <select name="classroomID" id="selectClassroom" class="form-control" >

                                                <c:forEach items="${requestScope.classroom}" var="subject" >
                                                    <option value="${subject.id}"
                                                            <c:if test="${student.classroom==subject}">selected</c:if>   >
                                                            ${subject.name}
                                                    </option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">账号</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="inputID" name="studentId"
                                                   value="${student.studentId}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">密码</label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control" id="inputPassword"
                                                   name="password"
                                                   placeholder="如需修改，请输入">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="name" name="name"
                                                   value="${student.name}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">性别</label>
                                        <div class="col-sm-8">
                                            <label class="radio-inline">
                                                <input type="radio" name="gender" value="1" checked> 男
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="gender" value="0"> 女
                                            </label>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="selectRole" class="col-sm-2 control-label">身份</label>

                                        <div class="col-sm-8">
                                            <select name="roleID" id="selectRole" class="form-control">
                                                <c:forEach items="${requestScope.roles}" var="role">
                                                    <option value="${role.id}"
                                                            <c:if test="${student.role==role}">selected</c:if>   >
                                                            ${role.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">身份证号码</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="inputIDCard" name="idCardNo"
                                                   value="${student.idCardNo}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" id="inputEmail" name="mail"
                                                   value="${student.mail}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputTel" class="col-sm-2 control-label">电话</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="inputTel" name="telephone"
                                                   value="${student.telephone}">
                                        </div>
                                    </div>
                                    <div class="col-sm-offset-2">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button id="cancel" class="btn btn-default">取消</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- end content -->

