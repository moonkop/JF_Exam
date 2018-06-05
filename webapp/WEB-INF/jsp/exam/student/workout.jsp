<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><tiles:getAsString name="title" ignore="true"/></title>
    <tiles:insertAttribute name="header" ignore="true"/>
    <tilesx:useAttribute id="list" name="additional-headers" classname="java.util.List" ignore="true"/>
    <c:forEach var="item" items="${list}">
        <tiles:insertAttribute value="${item}" flush="true"/>
    </c:forEach>
</head>
<body>

<div id="wrapper">

    <div class="exam-toolbar">
        <button class="btn btn-default" id="switch-view"> 切换视图</button>
        <button class="btn btn-danger pull-right" id="submit-paper"> 提交试卷</button>
        <span class="time-area pull-right">剩余时间：<span id="left-time">10:10</span></span>


    </div>
    <div class="paper-wrapper">
        <div style="height: 50px;"></div>
        <div class="paper-exam">
            <div class="title-area">
                <div class="title"></div>
                <div class="comment"></div>
            </div>
            <div class="question-list">

            </div>
        </div>
    </div>
    <script src="/dist/js/question-manage.js"></script>
    <%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

    <script>
        var app = {
            name: "exam-workout",
            exam: {
                id: '${exam.id}',
                studentExam: {
                    id: "${studentExam.id}",
                    startTime: "${studentExam.startTime}"
                },
                duration: "${exam.duration}"
            },
            paper: {
                id: '${paper.id}',
                title: '${paper.title}',
                comment: '${paper.comment}',
                questionList:${questionList},
            },
            currentTime: "${currentTime}"
        }

        $(document).ready(function () {
            //用index为数组下标重组questionlist
            var questionlist = [];
            app.paper.questionList.map(function (item) {
                    questionlist[item.index] = item;
                    item.needUpdate = false;
                }
            )
            app.paper.questionList = questionlist;
            //渲染试卷标题
            render_paper_title();
            //渲染试卷题目列表
            paper_exam_render_question_list();
            //从服务器获得作答
            get_workout_from_server(function (res) {
                //将作答存入questionList
                res.payload.rows.map(function (item) {
                    var question = app.paper.questionList[item.index - 1];
                    question.id = item.id;
                    question.workout = item.workout;
                    set_workout(question);
                })
                //自动保存定时器
                autoSaveTimer = setInterval(auto_upload_workout, 5000);
            })

            var timeLeft = get_remain_time();


            var timer = setInterval(function () {
                set_left_time(timeLeft--);
            }, 1000);

            $("#submit-paper").on("click", function () {
                layer.confirm("确定要交卷吗？交卷后不能再进入考场哦！", function () {
                    upload_all_workout_submit_paper();
                });
            })
        });

        //计算出剩余时间
        function get_remain_time()
        {
            return
        }

        //设置倒计时时间
        function set_left_time(time)
        {
            $("#left-time").text(convertTimeToStr(time));
        }


        //收集修改过的作答
        function collect_workout()
        {
            app.paper.questionList.map(function (item) {
                var workout = get_workout_form_question_index(item.index);
                if (workout != item.workout)
                {
                    item.needUpdate = true;
                    item.workout = workout;
                    console.log(item.index + "need update");
                }
            });
        }


        //自动上传作答（只上传更改过的）
        function auto_upload_workout()
        {
            collect_workout();

            var data = [];
            app.paper.questionList.map(function (item) {
                if (item.needUpdate == true)
                {
                    data.push({
                        id: item.id,
                        workout: item.workout
                    });
                }
            })
            if (data.length == 0)
            {
                return;
            }

            upload_workout(data, function (res) {
                    var dataWithId = {};
                    data.map(function (item) {
                        dataWithId[item.id] = item;
                    })
                    app.paper.questionList.map(function (item) {
                        if (dataWithId[item.id] != undefined)
                        {
                            item.needUpdate = false;
                        }
                    })
                    layer.msg("保存成功");
                }
            );
        }

        //上传所有作答
        function upload_all_workout_submit_paper()
        {

            var data = [];

            app.paper.questionList.map(function (item) {
                data.push({
                    id: item.id,
                    workout: item.workout
                });
            })

            upload_workout(data, function () {
                    get_workout_from_server(function (res) {
                            var success = true;
                            res.payload.rows.map(function (item) {

                                if (app.paper.questionList[item.index - 1].workout != item.workout)
                                {
                                    console.log(item.index + "check error");
                                    success = false;
                                } else
                                {
                                    console.log(item.index + "check passed");

                                }
                            })
                            if (success == true)
                            {
                                myajax(
                                    {
                                        url:"/exam/student/submit.do",
                                        data:{
                                            id:app.exam.studentExam.id
                                        },
                                        success:function(res) {
                                            layer.msg("交卷成功");
                                            setTimeout(function () {
                                                window.location.href = "/exam/student";
                                            },1000)

                                        }
                                    }
                                );

                            }
                            else
                            {
                                layer.alert("交卷失败");
                            }

                        }
                    )
                }
            )
        }


        //向服务器上传作答
        function upload_workout(data, success, error)
        {

            myajax(
                {
                    url: "/exam/student/archive.do",
                    type: 'post',
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify({
                            id: app.exam.studentExam.id,
                            workouts: data
                        }
                    ),
                    success: success,
                    error: error
                }
            )
        }

        function get_workout_from_server(success)
        {
            myajax(
                {
                    url: "/exam/student/getWorkout.do",
                    data: {
                        id: app.exam.studentExam.id
                    },
                    success: success
                }
            )
        }


        function get_workout_form_question_index(index)
        {
            var $panel = $(".panel-question[data-index='" + index + "']");
            var workout = "";
            switch (QuestionTypeMap($panel.attr("data-type")))
            {
                case '单选题':
                    $option = $panel.find("input:radio:checked");
                    if ($option.length != 0)
                    {
                        workout = $option.attr("data-option-index")
                    }
                    break;
                case '多选题':
                    var $options = $panel.find("input:checkbox:checked");

                    if ($options.length != 0)
                    {
                        $options.map(function (index, item) {
                                workout += $(this).attr("data-option-index") + ',';
                            }
                        )
                    }
                    break;
                case '简答题':
                    workout = $panel.find("textarea").val();
                    break;
            }
            workout == "" && (workout = null);
            return workout;
        }

        function set_workout(question)
        {
            var $panel = $(".panel-question[data-index='" + question.index + "']");
            if(question.workout==null)
            {
                return;
            }
            var workout = question.workout.split(',').filter(function (item) {
                    return item != "";
                }
            );

            switch (QuestionTypeMap($panel.attr("data-type")))
            {
                case '单选题':
                    if (workout.length != 0)
                    {
                        var $option = $panel.find("[data-option-index='" + workout[0] + "']");
                        $option.attr("checked", true);
                    }
                    break;
                case '多选题':
                    workout.map(function (item) {
                        var $option = $panel.find("[data-option-index='" + item + "']");
                        $option.attr("checked", true);
                    });
                    break;
                case '简答题':
                    answer = $panel.find("textarea").val(workout);
                    break;
            }
        }

    </script>


</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>






