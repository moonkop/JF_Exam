
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        $("#cancel").on("click", function () {
            window.history.go(-1);
        })

    })
</script>

<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            试卷管理 - 添加
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal" action="/paper/add.do"
                              method="post">


                            <div class="form-group">
                                <label class="col-sm-2 control-label">试卷标题</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="title" name="title">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">试卷备注</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="comment" name="comment">
                                </div>
                            </div>

                            <%--<input value="${User.id}" name="teacher_id" style="display: none" id="id">--%>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">科目</label>

                                <div class="col-sm-8">
                                    <select name="subject_id" id="subject_id" class="form-control">
                                        <c:forEach items="${subjectList}" var="subject">
                                            <option value="${subject.id}">
                                                    ${subject.name}
                                            </option>
                                        </c:forEach>
                                    </select>
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

