<%@ page import="com.njmsita.exam.manager.model.ClassroomVo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.njmsita.exam.manager.model.ExamVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->
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
                                <label class="col-sm-2 control-label" for="input-name">考试名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-name" name="name"
                                           value="${exam.name}" placeholder="请输入考试名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">考试备注</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-remark" name="remark"
                                           value="${exam.remark}" placeholder="请输入考试名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">考试班级</label>
                                <div class="col-sm-8">
                                    <select class="form-control js-select2" multiple="multiple" name="classroom">
                                        <c:forEach items="${requestScope.classrooms}" var="classroom">
                                            <option value="${classroom.id}">
                                                    ${classroom.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">开始时间</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control js-lay-datetime" id="input-start_time"
                                           name="openTime" placeholder="请输入开始时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">结束时间</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control js-lay-datetime" id="input-finish_time"
                                           name="closeTime"
                                           placeholder="请输入结束时间">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">入场时间</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type=text" class="form-control" id="input-enter_time" name="openDuration"
                                               value="${exam.openDuration}" placeholder="请输入场时间">
                                        <span class="input-group-addon">分钟</span>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">答题时间</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="input-duration" name="duration"
                                               value="${exam.duration}" placeholder="请输入答题时间">
                                        <span class="input-group-addon">分钟</span>
                                    </div>

                                </div>
                            </div>


                            <div class="form-group">
                                <label for="select-subject" class="col-sm-2 control-label">科目</label>
                                <div class="col-sm-8">
                                    <select name="subject" id="select-subject" class="form-control">
                                        <c:forEach items="${requestScope.subjects}" var="subject">
                                            <option value="${subject.id}"
                                                    <c:if test="${SelectedSubject==subject}">selected</c:if>
                                            >
                                                    ${subject.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="select-paper" class="col-sm-2 control-label">试卷</label>

                                <div class="col-sm-8">
                                    <select name="paper" id="select-paper" class="form-control">
                                    </select>
                                </div>
                            </div>


                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <button class="btn btn-default js-cancel">取消</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>
<script>

    var app = {
        name: "exam-edit",
        exam: {
            openTime: emptyToUndefined(${exam.openTime}),
            closeTime: emptyToUndefined(${exam.closeTime})
        }
    }
    $(document).ready(function () {

        function getPapers()
        {
            var subject = $("#select-subject").val();
            $.ajax(
                {
                    url: "/paper/list.do",
                    data: {
                        'subject.id': subject,
                        'pageNum': 1,
                        'pageSize': 1000
                    },
                    success: function (res) {
                        OnResult(res, function (res) {
                                $("#select-paper").empty();
                                var papers = res.payload.rows;
                                papers.map(function (item) {
                                        $("#select-paper").append(
                                            '<option value=' + item.id + '>' + item.title + '</option>'
                                        )
                                    }
                                )
                            }
                        )
                    }
                }
            )

        }


        $("form").on("submit", function () {
            $.ajax(
                {
                    url: "/exam/manage/edit.do",
                    data: exam_edit_form_collect_data(),
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
        $("#select-subject").on("change", function () {
            getPapers();
        })
        getPapers();
        laydate.render({
            elem: '#input-start_time'
            , type: 'datetime'
        });
        laydate.render({
            elem: '#input-finish_time'
            , type: 'datetime'
        });
        if (app.exam.closeTime == undefined)
        {
            app.exam.closeTime = Date.now();
        }
        if (app.exam.openTime == undefined)
        {
            app.exam.openTime = Date.now();
        }
        $("#input-start_time").val(TimeStampTDateTimeString(app.exam.openTime));
        $("#input-finish_time").val(TimeStampTDateTimeString(app.exam.closeTime));
        var classroomIdStr = emptyToUndefined("${exam.classroomIds}");
        if (classroomIdStr!=undefined)
        {
            strs=classroomIdStr.split(",");
            $(".js-select2").val(strs);
        }

        $(".js-select2").select2();
    })


    function exam_edit_form_collect_data()
    {
        var data = {
            'id': $("form [name='id']").val(),
            'name': $("form [name='name']").val(),
            'duration': $("form [name='duration']").val(),
            'openDuration': $("form [name='openDuration']").val(),
            'remark': $("form [name='remark']").val(),
            'paperId': $("form [name='paper']").val(),
            'subject.id': $("form [name='subject']").val(),
            'openTime': DateTimeStringToTimeStamp($("form [name='openTime']").val()),
            'closeTime': DateTimeStringToTimeStamp($("form [name='closeTime']").val()),
            '_classroomIds': $("form [name='classroom']").val(),
        };
        return data
    }

    function getClassroomIds()
    {

    }

</script>
<!-- end content -->
