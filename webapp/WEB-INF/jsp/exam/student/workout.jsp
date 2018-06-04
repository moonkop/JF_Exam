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
                    id: "${studentExam.id}"
                }
            },
            paper: {
                id: '${paper.id}',
                title: '${paper.title}',
                comment: '${paper.comment}',
                questionList:${questionList},
            },
            answers: {}
        }

        $(document).ready(function () {
            $(".side-visible-line").click();



            app.paper.questionList.map(function (item) {
                questionlist[item.index] = item;
                item.needUpdate = false;
            })

            get_workout_from_server(function (res) {
                res.payload.rows.map(function (item) {
                    var question = questionlist[item.index - 1];
                    question.id = item.id;
                    question.workout = item.workout;
                })
                autoSaveTimer = setInterval(auto_upload_workout, 5000);
            })

            render_paper_title();
            paper_exam_render_question_list();

            var timeleft = 36000;


            var timer = setInterval(function () {
                set_left_time(timeleft--);
            }, 1000);

            $("#submit-paper").on("click", function () {
                upload_all_workout();
            })
        });


        function set_left_time(time)
        {
            $("#left-time").text(convertTimeToStr(time));
        }

        var questionlist = [];

        function init()
        {


        }

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

            upload_workout(data);
        }

        function upload_all_workout()
        {

            var data = [];

            app.paper.questionList.map(function (item) {
                data.push({
                    id: item.id,
                    workout: item.workout
                });
            })

            upload_workout(data)
        }


        function upload_workout(data)
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
                    success: function (res) {
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
                    }
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
            var workout=question.workout;

            switch (QuestionTypeMap($panel.attr("data-type")))
            {
                case '单选题':



                    break;
                case '多选题':




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






