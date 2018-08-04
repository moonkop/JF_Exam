<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script>

    var app = {
        name: "exam-edit",
        exam: {
            openTime: emptyToUndefined(${exam.openTime}),
            closeTime: emptyToUndefined(${exam.closeTime}),
            duration:emptyToUndefined(${exam.duration}),
            openDuration:emptyToUndefined(${exam.openDuration}),
            classroomIds: [
                <c:forEach items="${exam.classrooms}" var="classroom">
                '${classroom.id}',
                </c:forEach>
            ]
        }
    }

    $(document).ready(function () {

        function getPapers()
        {
            var subject = $("#select-subject").val();


            if (User.role == '0')
            {
                $("#is-start-immediately").show();
            }
            myajax({
                url: "/paper/list.do",
                data: {
                    'subject.id': subject,
                    'pageNum': 1,
                    'pageSize': 1000
                },
                success: function (res) {
                    $("#select-paper").empty();
                    var papers = res.payload.rows;
                    papers.map(function (item) {
                            $("#select-paper").append(
                                '<option value=' + item.id + '>' + item.title + '</option>'
                            )
                        }
                    )
                }
            });

        }


        $("form").on("submit", function () {
            var data = exam_edit_form_collect_data();
            if (data == undefined)
            {
                return false;
            }
            $.ajax(
                {
                    url: "/exam/manage/edit.do",
                    data: JSON.stringify(data) ,
                    type: 'post',
                    dataType: 'json',
                    contentType: "application/json",
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
        $("#is-limit-duration").on("change", function () {
            if ($("#is-limit-duration").prop("checked"))
            {
                $(".js-form-group-duration").show();
            } else
            {
                if ($("#is-limit-open-duration").prop("checked"))
                {
                    $("#is-limit-duration").prop("checked", true);

                    layer.msg("限制入场时间必须限制答题时间");

                } else
                {
                    $(".js-form-group-duration").hide();

                }
            }
        })
        $("#is-limit-open-duration").on("change", function () {
            if ($("#is-limit-open-duration").prop("checked"))
            {
                $("#is-limit-open-duration").prop("checked") && $(".js-form-group-open-duration").show();
                $("#is-limit-duration").prop("checked", true);
                $("#is-limit-duration").trigger("change");
            } else
            {
                $("#is-limit-open-duration").prop("checked") || $(".js-form-group-open-duration").hide();
            }
        })

        $("#is-limit-duration,#is-limit-open-duration").on("change", function () {
            if ($("#is-limit-open-duration").prop("checked") && $("#is-limit-duration").prop("checked"))
            {

                $("#input-finish_time").prop("disabled", true);
                autoSetEndTime();
                $("#input-start_time,#input-duration,#input-enter_time").on("change", autoSetEndTime);
            } else
            {
                $("#input-finish_time").prop("disabled", false);
                $("#input-start_time,#input-duration,#input-enter_time").off("change", autoSetEndTime);
            }
        })
        var timer="";
        $("#is-start-immediately").on("change",function()
        {
            autoSetStartTime();
            if($("#is-start-immediately").prop("checked"))
            {
                $("#input-start_time").prop("disabled",true);
                timer=setInterval(autoSetStartTime,1000);
            }else{
                $("#input-start_time").prop("disabled",false);
                clearInterval(timer);
            }

        })

        function autoSetStartTime()
        {
            $("#input-start_time").val(TimeStampTDateTimeString(new Date().getTime()));
            $("#input-start_time").trigger("change");
        }

        function autoSetEndTime()
        {
            var time = DateTimeStringToTimeStamp($("#input-start_time").val()) + $("#input-duration").val() * 60 * 1000 + $("#input-enter_time").val() * 60 * 1000;
            $("#input-finish_time").val(TimeStampTDateTimeString(time));
            $("#input-finish_time").trigger("change");
        }


        getPapers();
        laydate.render({
            elem: '#input-start_time'
            , type: 'datetime',
            done: function (value, date) {
                $("#input-start_time").val(value);
                $("#input-start_time").trigger("change");
            }
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
        if(app.exam.duration!=0&&app.exam.duration!=undefined)
        {
            $("#is-limit-duration").prop("checked", true);
            $("#is-limit-duration").trigger("change");

        }
        if (app.exam.openDuration!=0&&app.exam.openDuration!=undefined)
        {
            $("#is-limit-open-duration").prop("checked", true);
            $("#is-limit-open-duration").trigger("change");
        }
        $("#input-start_time").val(TimeStampTDateTimeString(app.exam.openTime));
        $("#input-finish_time").val(TimeStampTDateTimeString(app.exam.closeTime));
        $(".js-select2").val(app.exam.classroomIds);

        $(".js-select2").select2();
    })

    function exam_edit_form_collect_data()
    {
        var data = {
            exam:{
                'id': $("form [name='id']").val(),
                'name': $("form [name='name']").val(),
                'duration': $("form [name='duration']").val(),
                'openDuration': $("form [name='openDuration']").val(),
                subject:{
                  'id':  $("form [name='subject']").val()
                },
                'openTime': DateTimeStringToTimeStamp($("form [name='openTime']").val()),
                'closeTime': DateTimeStringToTimeStamp($("form [name='closeTime']").val()),
            },
            'paperId': $("form [name='paper']").val(),
            '_classroomIds': $("form [name='classroom']").val(),
            'immediately':$("#is-start-immediately").prop("checked")
        };


        if ($("#is-limit-open-duration").prop("checked") && $("#input-enter_time").val() == 0)
        {
            layer.alert("限制入场时间时，入场时间不能为空");
            return;
        }
        if ($("#is-limit-duration").prop("checked") && $("#input-duration").val() == 0)
        {
            layer.alert("限制答题时间时，答题时间不能为空")
            return;
        }

        $("#is-limit-open-duration").prop("checked") || (data.openDuration = 0);
        $("#is-limit-duration").prop("checked") || (data.duration = 0);
        return data;
    }


</script>


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
                                <label class="col-sm-2 control-label" for="input-name">考试班级</label>
                                <div class="col-sm-8">
                                    <select class="form-control js-select2" multiple="multiple" name="classroom">
                                        <c:forEach items="${requestScope.classroomList}" var="classroom">
                                            <option value="${classroom.id}">
                                                    ${classroom.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="select-subject" class="col-sm-2 control-label">科目</label>
                                <div class="col-sm-8">
                                    <select name="subject" id="select-subject" class="form-control">
                                        <c:forEach items="${requestScope.subjects}" var="subject">
                                            <option value="${subject.id}">
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


                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-start_time">开始时间</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control js-lay-datetime" id="input-start_time"
                                           name="openTime" placeholder="请输入开始时间">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-finish_time">结束时间</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control js-lay-datetime" id="input-finish_time"
                                           name="closeTime"
                                           placeholder="请输入结束时间">
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-8">

                                    <label class="checkbox" for="is-limit-duration">
                                        <input type="checkbox" id="is-limit-duration">
                                        限制答题时间
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-8">

                                    <label class="checkbox" for="is-limit-open-duration">
                                        <input type="checkbox" id="is-limit-open-duration">
                                        限制入场时间
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <div class="col-sm-8">
                                    <label class="checkbox" for="is-start-immediately">
                                        <input type="checkbox" id="is-start-immediately" hidden>
                                        立刻开始
                                    </label>
                                </div>
                            </div>

                            <div class="form-group js-form-group-duration" hidden>
                                <label class="col-sm-2 control-label" for="input-name">答题时间</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="input-duration" name="duration"
                                               value="${exam.duration}" placeholder="请输入答题时间">
                                        <span class="input-group-addon">分钟</span>
                                    </div>

                                </div>
                            </div>

                            <div class="form-group js-form-group-open-duration" hidden>
                                <label class="col-sm-2 control-label" for="input-name">入场时间</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type=text" class="form-control" id="input-enter_time" name="openDuration"
                                               value="${exam.openDuration}" placeholder="请输入场时间">
                                        <span class="input-group-addon">分钟</span>
                                    </div>
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

<!-- end content -->
