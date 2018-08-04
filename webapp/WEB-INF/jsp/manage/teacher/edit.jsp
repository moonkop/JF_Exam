<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script>
    MyAjaxForm("form");
</script>
        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    教师管理 - 添加
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/teacher/manage/edit.do"
                                      method="post">

                                    <input type="text" class="form-control" id="id" name="id" style="display: none"
                                           value="${teacher.id}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">账号</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="inputID" name="teacherId"
                                                   value="${teacher.teacherId}">
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
                                                   value="${teacher.name}">
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
                                            <select name="role.id" id="selectRole" class="form-control">
                                                <c:forEach items="${requestScope.roles}" var="role">
                                                    <option value="${role.id}"
                                                            <c:if test="${teacher.role==role}">selected</c:if>   >
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
                                                   value="${teacher.idCardNo}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" id="inputEmail" name="mail"
                                                   value="${teacher.mail}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputTel" class="col-sm-2 control-label">电话</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="inputTel" name="telephone"
                                                   value="${teacher.telephone}">
                                        </div>
                                    </div>
                                    <div class="col-sm-offset-2">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button class="btn btn-default js-back">取消</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- end content -->
