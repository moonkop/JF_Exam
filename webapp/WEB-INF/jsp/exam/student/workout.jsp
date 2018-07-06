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

<div id="wrapper" style="max-width: 800px;margin: 0 auto">
    <div class="exam-toolbar">
        <span class="exam-title">${exam.name}</span>
        <%--<button class="btn btn-default" id="switch-view"> 切换视图</button>--%>
        <button class="btn btn-default pull-right" id="submit-paper"> 提交试卷</button>
        <%--<span class="time-area pull-right">剩余时间：<span id="left-time">10:10</span></span>--%>


    </div>
    <div class="paper-wrapper">
        <div class="paper-exam" style="margin-top: 50px">
            <div class="title-area">
                <div class="title"></div>
                <div class="comment"></div>
            </div>
            <div class="question-list">

            </div>

            <div class="answer-card">
                <div class="count-down-area" style="display: none;">
                    <div class="answer-card-title">
                        剩余时间（分秒）
                    </div>
                    <div class="answer-card-content" id="count-down">
                        25:31
                    </div>
                </div>
                <div class="answer-card-title">
                    完成率
                </div>
                <div class="answer-card-content" id="complete-rate">
                    50%
                </div>
                <div class="answer-card-title">
                    第一部分
                </div>
                <div class="answer-card-list">
                </div>

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
                duration: "${exam.duration}" * 60 * 1000,
                closeTime: "${exam.closeTime}"
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

            app.paper.questionList = sort_question_List(app.paper.questionList);

            //渲染试卷标题
            render_paper_title();
            //渲染试卷题目列表
            paper_exam_render_question_list();
            //从服务器获得作答
            get_workout_from_server(function (res) {
                //将作答存入questionList
                res.payload.rows.map(function (item) {
                    var question = app.paper.questionList[item.index];
                    question.id = item.id;
                    question.workout = item.workout;
                    set_workout(question);
                })
                refresh_answer_card();
                //自动保存定时器
                autoSaveTimer = setInterval(auto_upload_workout, 5000);
            })
            set_up_count_down();
            render_answer_card();

            $("#submit-paper").on("click", function () {
                layer.confirm("确定要交卷吗？交卷后不能再进入考场哦！", function () {
                    upload_all_workout_submit_paper();
                });
            })
            $(".question-list").on("change", "input,textarea", function () {
                collect_workout();
            })
            $(".answer-card-list").on("click", "span", function () {
                navigate_to_question($(this).attr("data-index"));
            })
        });


        function set_up_count_down()
        {
            if (app.exam.closeTime - app.currentTime < 0)
            {
                layer.alert("考试已经结束！");
                return;
            }
            if (app.exam.duration == 0)
            {
                if (app.exam.closeTime - app.currentTime < 1000 * 3600 * 3)
                {
                    layer.alert("考试将于" + TimeStampTDateTimeString(app.exam.closeTime) + "结束，请尽快作答，超过时间将无法交卷！");
                }
                return;
            }
            $(".count-down-area").show();

            var timeLeft = get_remain_time();
            timer = setInterval(function () {
                set_left_time(timeLeft -= 1000);
                if (timeLeft < 0)
                {
                    $(".time-area").hide();
                    clearInterval(timer);
                    layer.msg("作答时间到，10秒后将自动交卷")
                    setTimeout(function () {
                        upload_all_workout_submit_paper();
                    }, 10000);
                }
            }, 1000);
        }

        //计算出剩余时间
        function get_remain_time()
        {
            var left_workout_time = app.exam.duration - (app.currentTime - app.exam.studentExam.startTime)
            var left_close_time = app.exam.closeTime - app.currentTime;
            return left_workout_time < left_close_time ? left_workout_time : left_close_time;
        }

        //设置倒计时时间
        function set_left_time(time)
        {
            $("#count-down").text(ConvertTimeDuration(time));
        }

        var $workout_list;

        function render_answer_card()
        {
            $workout_list = $(".answer-card-list");
            $workout_list.empty();
            app.paper.questionList.map(function (item) {
                  var  html = "<span id='answer_item_" + item.index + "'data-index='" + item.index + "' >" + (parseInt(item.index) + 1) + "</span>"
                    $workout_list.append(html);
                }
            )
        }


        function refresh_answer_card()
        {
            var question_completed = 0;
            app.paper.questionList.map(function (item) {
                    var $answer_card_item = $workout_list.find("#answer_item_" + item.index);
                    $answer_card_item.removeClass();
                    if (item.workout != null && item.workout != "")
                    {
                        $answer_card_item.addClass("completed");
                        question_completed++;
                        if (item.needUpdate == false)
                        {
                            $answer_card_item.addClass("up-to-date");
                        }
                    }
                }
            )
            $("#complete-rate").text(Number(question_completed / app.paper.questionList.length * 100).toFixed(1) + "%")
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
            refresh_answer_card();
        }

        //自动上传作答（只上传更改过的）
        function auto_upload_workout()
        {
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
                    });
                    refresh_answer_card();
                    layer.msg("保存成功", {
                        offset: 't',
                    });
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

                                if (app.paper.questionList[item.index].workout != item.workout)
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
                                        url: "/exam/student/submit.do",
                                        data: {
                                            id: app.exam.studentExam.id
                                        },
                                        success: function (res) {
                                            layer.msg("交卷成功");
                                            setTimeout(function () {
                                                window.location.href = "/exam/student";
                                            }, 1000)

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
            var $panel = app.paper.questionList[index].$panel;
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



    </script>


</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>






