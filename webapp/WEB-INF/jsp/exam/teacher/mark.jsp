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
        <button class="btn btn-default pull-left" id="prev-paper"> 上一张</button>
        <button class="btn btn-default pull-right" id="next-paper"> 下一张</button>
        <button class="btn btn-danger pull-right" id="save-mark"> 保存</button>
    </div>

    <div class="paper-wrapper">

        <div class="paper-mark" style="margin-top: 50px">
            <div class="student-info">
                <span class="student-info-name">

                </span>
                <span class="student-info-school">

                </span>
                <span class="student-info-classroom">

                </span>
                <span class="student-info-id">

                </span>
            </div>
            <div class="question-list">
                <div class="panel panel-question" id="paper_question_2" data-index="2" data-type="4">
                    <div class="panel-body">
                        <div class="question-header">
                            <span class="question-index">3. </span>

                            <span class="question-type">简答题</span>

                            <span class="question-answer"></span>

                            <span class="question-value">10分</span>

                            <span class="question-actions"></span>
                        </div>
                        <div class="question-body">
                            <span class="question-outline">我是第三道题</span>
                            <div class="question-code">
                                <pre><code class="hljs"></code></pre>
                            </div>
                        </div>
                        <div class="question-workout">
                                <pre style="width: 100%">
                                    123456789
                                    123
                                    123
                                    123
                                    123
                                </pre>
                        </div>
                        <div class="question-mark">
                            <div class="question-mark-info">
                                    <span class="question-mark-info-teacher">
                                        批阅人：
                                        <span>徐强</span>
                                    </span>
                                <span class="question-mark-info-time">
                                        <span>2018年6月24日19:26:00</span>
                                    </span>
                            </div>
                            <div class="question-mark-area">
                                <div class="question-mark-remark">
                                    <textarea class="question-remark-area"></textarea>
                                </div>
                                <div class="question-mark-range">
                                    <input type="text" class="js-question-mark-range">
                                </div>
                                <div class="question-mark-score">
                                    <input type="text" class="question-mark-score-input">
                                    <span>分</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <div class="navcard">
                <div class="navcard-title">
                    题目列表
                </div>

                <div class="navcard-workout-list">


                </div>
                <div class="navcard-title">
                    试卷导航
                </div>
                <div class="navcard-paper-list">

                </div>

                <div class="navcard-title">
                    试卷导航
                </div>

                <div class="navcard-paper-list navcard-paper-list-open">

                </div>


            </div>
        </div>
    </div>
    <script src="/dist/js/question-manage.js"></script>
    <%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

    <script>


        var User = {
            id: '${loginUser.id}',
            name: '${loginUser.name}',
            role: '${loginUser.role.id}'
        }
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
            currentTime: "${currentTime}",
            paperList: [],
            currentStudentExam:
                {
                    id: null,
                    workout: null
                },
            currentPaperIndex: null
        }


        var navcard = {
            $this: $(".navcard"),
            $workout_list: $(".navcard-workout-list"),
            navigate_to_question: navigate_to_question,
            render_paper_list: function () {
                var $paper_list = $(".navcard-paper-list");
                $paper_list.empty();
                app.paperList.map(function (item) {
                    var $span = $("<span></span>");
                    $span.attr("id", "paper_item_" + item.index);
                    $span.attr("data-index", item.index);
                    $span.attr("data-id", item.id);
                    $span.html(parseInt(item.index) + 1);
                    var mark_status_className_map = {
                        "completed": "navcard-item-completed",
                        "notStarted": "navcard-item-notstarted",
                        "unfinished": "navcard-item-unfinished",
                        "notFound": "navcard-item-disabled",
                    }
                    $span.addClass(mark_status_className_map[item.mark_status]);
                    $paper_list.append($span);
                })
            },

            render_workout_list: function () {
                $(".navcard-workout-list").empty();
                app.paper.questionList.map(function (item) {
                        var $span = $("<span></span>");
                        $span.attr("id", "workout_item_" + item.index);
                        $span.attr("data-index", item.index);
                        $span.text((parseInt(item.index) + 1));
                        $(".navcard-workout-list").append($span);
                    }
                )
            },

            //刷新试卷中题目批改状态
            refresh_workout_list: function () {
                app.currentStudentExam.workout.map(function (item) {
                    var $workout_item = $(".navcard-workout-list").find("#workout_item_" + item.index);
                    $workout_item.removeClass();
                    if (item.score != null && item.score != undefined)
                    {
                        $workout_item.addClass("navcard-item-completed");
                    }
                    if ((item.teacherId != null && item.teacherId != undefined && item.teacherId == User.id) || item._question.needUpdate == true)
                    {
                        $workout_item.addClass("navcard-item-marked-by-me");
                    }


                });
            },

            set_paper_active: function (id) {
                this.$this.find(".navcard-item-active").removeClass("navcard-item-active");
                this.$this.find("[data-id='" + id + "']").addClass("navcard-item-active");
            },
            init: function () {
                this.render_workout_list();

                $(".navcard-workout-list").on("click", "span", function () {
                    navcard.navigate_to_question($(this).attr("data-index"));
                })

                $(".navcard-paper-list").on("click", "span", function () {
                    if ($(this).hasClass("navcard-item-disabled"))
                    {
                        return false;
                    }

                    switch_paper($(this).attr("data-index"));
                    ;
                })
            }
        };

        function switch_next_paper()
        {
            var index = app.currentPaperIndex;
            while (index < app.paperList.length)
            {
                index++;
                if (app.paperList[index].mark_status != "notFound")
                {
                    switch_paper(index);
                    return;
                }
            }
            layer.msg("未找到试卷");
        }

        function switch_prev_paper()
        {
            var index = app.currentPaperIndex;
            while (index >= 0)
            {
                index--;
                if (app.paperList[index].mark_status != "notFound")
                {
                    switch_paper(index);
                    return;
                }
            }
            layer.msg("未找到试卷");
        }

        function switch_paper(index)
        {
            var studentExamId = app.paperList[index].id;
            get_workout_from_server(studentExamId, function (res) {
                    navcard.set_paper_active(studentExamId);
                    app.currentPaperIndex = app.paperIDtoIndex[studentExamId].index;
                    app.currentStudentExam.id = studentExamId;
                    app.currentStudentExam.workout = [];
                    res.payload.workoutList.map(function (item) {
                        app.currentStudentExam.workout[item.index] = item;
                    });
                    set_student_info(res.payload.student);
                    res.payload.workoutList.map(function (item) {
                        var question = app.paper.questionList[item.index];
                        item._question = question;
                        question.setMarkTeacher(item.teacher);
                        question.setScore(item.score);
                        question.$remark.val(item.remark);
                        question.$workout.html(item.workout);
                        question.workoutId = item.id;
                    });
                    navcard.refresh_workout_list();
                }
            )
        }

        function set_student_info(student)
        {
            $(".student-info-name").text(student.name);
            $(".student-info-school").text(student.school);
            $(".student-info-classroom").text(student.classroom);
            $(".student-info-id").text(student.id);
        }

        function get_workout_from_server(studentExamId, callback)
        {
            myajax(
                {
                    url: "/exam/operation/getWorkout.do?id=" + studentExamId,
                    success: callback
                }
            );
        }


        function initQuestion(item)
        {
            var $panel = $(".panel-question[data-index='" + item.index + "']");
            var $score_input = $panel.find(".question-mark-score-input");
            var $slider = $panel.find(".js-question-mark-range");
            $slider.ionRangeSlider(
                {
                    grid: true,
                    min: 0,
                    max: item.value,
                    step: 0.5,
                    postfix: "分",
                    onChange: function (data) {
                        $score_input.val(data.from);
                        item.changed();
                    }
                }
            )
            var slider = $slider.data("ionRangeSlider");

            item.needUpdate = false;
            item.$panel = $panel;
            item.silder = slider;
            item.$slider = slider;
            item.$score_input = $score_input;
            item.$remark = $panel.find(".question-remark-area");
            item.$mark_teacher = $panel.find(".question-mark-info-teacher");
            item.$workout = $panel.find(".question-workout-area");

            $score_input.on("keyup,change", function () {
                var val = $score_input.val();
                //超过题目分值则为题目分值
                if (val > item.value)
                {
                    val = item.value;
                }
                //若不为0.5的倍数 则去尾
                if (parseInt(val * 2) != val * 2)
                {
                    val = parseInt(val * 2) / 2;
                }
                $score_input.val(val);
                //更新拖动条
                slider.update({
                    from: $score_input.val()
                })
                item.changed();
            })

            item.changed = function () {
                app.currentStudentExam.workout[item.index].score = $score_input.val()
                item.needUpdate = true;
                navcard.refresh_workout_list();
            }

            item.$remark.on("change", item.changed);

            item.get_mark = function () {
                return get_mark_by_question(this);
            }
            item.setMarkTeacher = function (teacher) {
                if (teacher != null && teacher != undefined && teacher != "")
                {
                    item.$mark_teacher.text("批阅人：" + teacher);
                }
            }
            item.setScore = function (score) {
                slider.update({
                    from: score
                });
                $score_input.val(score);
            };
        }


        function get_student_exam_list(callback)
        {
            myajax(
                {
                    url: "/exam/operation/getStudentExamList.do?id=" + app.exam.id,
                    success: function (res) {
                        app.paperList = [];
                        app.paperIDtoIndex = [];

                        res.payload.rows.map(function (item) {
                            app.paperList[item.index] = item;
                            app.paperIDtoIndex[item.id] = item;
                        })

                        navcard.render_paper_list();
                        if (typeof callback == "function")
                        {
                            callback(res)
                        }
                    }
                }
            );
        }

        function save_mark_changed()
        {
            var contentArr = [];
            var questionUpdated = [];
            var workoutUpdated = [];
            app.currentStudentExam.workout.map(function (item) {
                if (item._question.needUpdate == true)
                {
                    contentArr.push(item._question.get_mark());
                    questionUpdated.push(item._question);
                    workoutUpdated.push(item);
                }
            })
            if (contentArr.length != 0)
            {
                save_mark(contentArr, function (res) {
                    questionUpdated.map(function (item) {
                            item.needUpdate = false;
                        }
                    )
                    workoutUpdated.map(function(item)
                    {
                        item.teacherId = User.id;

                    })
                    layer.msg("自动保存成功", {
                        offset: 't',
                    })
                });
            }
        }

        function save_mark(contentArr, callback)
        {
            myajax(
                {
                    contentType: "application/json",
                    type: "post",
                    url: "/exam/operation/saveMark.do",
                    data:
                        JSON.stringify(contentArr)
                    ,
                    success: function (res) {
                        if (typeof callback == 'function')
                        {
                            callback(res);
                        }
                        else
                        {
                            layer.msg("保存成功", {
                                offset: 't',
                            })
                        }
                    }
                }
            );
        }

        function get_mark_by_question(question)
        {
            var obj = {};
            obj.id = question.workoutId;
            obj.score = question.$score_input.val();
            obj.remark = question.$remark.val();
            return obj;
        }

        function save_all_mark_to_server()
        {


            arr = [];
            app.paper.questionList.map(function (item) {
                if (item != null)
                {
                    arr.push(get_mark_by_question(item));
                }
            });

            myajax(
                {
                    contentType: "application/json",
                    type: "post",
                    url: "/exam/operation/saveMark.do",
                    data:
                        JSON.stringify(arr)
                    ,
                    success: function () {
                        layer.msg("保存成功");
                    }
                }
            );
        }

        $(document).ready(function () {
            app.paper.questionList = reArrangeQuestionList(app.paper.questionList);
            render_paper_title();
            paper_mark_render_question_list();

            get_student_exam_list(function (res) {
                switch_paper(0);
            });

            navcard.init();

            $("#save-mark").on("click", function () {
                save_all_mark_to_server();
            })

            $("#next-paper").on("click", function () {
                switch_next_paper();
            });
            $("#prev-paper").on("click", function () {
                switch_prev_paper();
            });

            app.paper.questionList.map(initQuestion)


            $(".question-mark-range").append();
            $(".js-question-mark-range").ionRangeSlider();
            var autoSaveTimer = setInterval(function () {
                save_mark_changed();
            }, 10000);
        });


    </script>


</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>


</title>
</head>
<body>

</body>
