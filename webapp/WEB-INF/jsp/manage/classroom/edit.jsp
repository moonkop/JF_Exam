<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<script>
    MyAjaxForm("form");
</script>

<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            班级管理
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal" action="/manage/classroom/edit.do">
                            <div class="form-group" style="display:none">
                                <input type="text" class="form-control" id="id" name="id" style="display: none"
                                       value="${classroom.id}">
                            </div>

                            <div class="form-group">
                                <label for="selectSchool" class="col-sm-2 control-label">学校</label>

                                <div class="col-sm-8">
                                    <select name="schoolVo.id" id="selectSchool" class="form-control">
                                        <c:forEach items="${requestScope.schools}" var="subject">
                                            <option value="${subject.id}"
                                                    <c:if test="${classroom.schoolVo==subject}">selected</c:if>
                                            >
                                                    ${subject.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group" >
                                <label class="col-sm-2 control-label" for="input-name">班级名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-name" name="name" value="${classroom.name}"
                                           placeholder="请输入班级名">
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <a class="btn btn-default" onclick="window.location.href='/manage/classroom'">取消</a>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>


<!-- end content -->
