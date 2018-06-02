<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script>

    var app = {
        name: "exam-addMarkTeacher",
        exam: {
            markTeachers: [
                <c:forEach items="${markTeacherList}" var="markTeacher">
                '${markTeacher.id}',
                </c:forEach>
            ]
        }
    }

    $(document).ready(function () {

        $("#cancel").on("click", function () {
            window.history.go(-1);
        })

        $("form").on("submit", function () {
            var data = exam_addMarkTeachers_form_collect_data();
            if(data==undefined)
            {
                return false;
            }
            $.ajax(
                {
                    url: "/exam/operation/addMarkTeacher.do",
                    data:data ,
                    type: "post",
                    success: function (res) {
                        OnResult(res, function (res) {
                                layer.msg("提交成功");
                                setTimeout(function () {
                                    window.location.href = "/exam/manage";
                                }, 1000)
                            }
                        )
                    },
                    error: defaultOnFailure

                }
            );
            return false;

        })


        $(".js-select2").val(app.exam.markTeachers);
        $(".js-select2").select2();

    })

    function exam_addMarkTeachers_form_collect_data ()
    {
        var data = {
            'id': $("form [name='id']").val(),
            '_markTeachers': $("form [name='markTeacher']").val(),
        };
        return data;
    }

</script>


<div class="row">
    <div class="col-lg-12">
        <h3>
            编辑考试
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal" action="/manage/classroom/edit.do">
                            <div class="form-group" style="display:none">
                                <input type="text" class="form-control" id="id" name="id" style="display: none"
                                       value="${exam.id}">
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">考试名称</label>
                                <div class="col-sm-8">
                                    <label class="form-control-static">${exam.name}</label>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">科目</label>
                                <div class="col-sm-8">
                                    <label class="form-control-static">${exam.subject.name}</label>
                                </div>
                            </div>





                            <div class="form-group">
                                <label class="col-sm-2 control-label" >阅卷老师</label>
                                <div class="col-sm-8">
                                    <select class="form-control js-select2" multiple="multiple" name="markTeacher">
                                        <c:forEach items="${requestScope.teacherList}" var="teacher">
                                            <option value="${teacher.id}">
                                                    ${teacher.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>





                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <button id="cancel" class="btn btn-default ">取消</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- end content -->

