<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script src="/dist/js/question-manage.js"></script>
<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>
<script>
    var app = {
        name: 'exam-preview',
        exam: {
            id: '${exam.id}',
            openTime: '${exam.openTime}',
            closeTime: "${exam.closeTime}",
            studentExam: {
                id: "${studentExam.id}",
                startTime: "${studentExam.startTime}",
                submitTime: "${studentExam.submitTime}",
                archive: emptyToUndefined(${student_exam_archive})
            }
        },
        paper: {
            id: '${paper.id}',
            title: '${paper.title}',
            comment: '${paper.comment}',
            questionList:${questionList},
        }
    };
    $(document).ready(function () {
        $("#openTime").text(TimeStampTDateTimeString(app.exam.openTime));
        $("#closeTime").text(TimeStampTDateTimeString(app.exam.closeTime));
        $("#startTime").text(TimeStampTDateTimeString(app.exam.studentExam.startTime));
        $("#submitTime").text(TimeStampTDateTimeString(app.exam.studentExam.submitTime));
        $("#workoutTime").text(ConvertTimeDuration(app.exam.studentExam.submitTime - app.exam.studentExam.startTime));
        app.paper.questionList = reArrangeQuestionList(app.paper.questionList);
        render_paper_title();
        exam_result_render_question_list();

    });

</script>
<div class="row">

<div class="col-lg-12">
<h3>
    考试结果
</h3>

<div class="panel panel-default">
    <div class="panel-heading">
        基本信息
    </div>
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">考试名称</label>
            <div class="col-sm-8">
                <p class="form-control-static">${exam.name}</p>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">满分</label>
            <div class="col-sm-8">
                <p class="form-control-static">${fullMark}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">总成绩</label>
            <div class="col-sm-8">
                <p class="form-control-static">${studentExam.score}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">百分制分数</label>
            <div class="col-sm-8">
                <p class="form-control-static">${scorePercent}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">开始时间</label>
            <div class="col-sm-8">
                <p class="form-control-static" id="openTime">${exam.openTime}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">结束时间</label>
            <div class="col-sm-8">
                <p class="form-control-static" id="closeTime">${exam.closeTime}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">入场时间</label>
            <div class="col-sm-8">
                <p class="form-control-static" id="startTime"></p>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">交卷时间</label>
            <div class="col-sm-8">
                <p class="form-control-static" id="submitTime"></p>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">用时</label>
            <div class="col-sm-8">
                <p class="form-control-static" id="workoutTime"></p>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">发起者</label>
            <div class="col-sm-8">
                <p class="form-control-static">${exam.createTeacher.name}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">考试班级</label>
            <div class="col-sm-8">
                <p class="form-control-static">
                    <c:forEach items="${exam.classrooms}" var="classroom">
                                            <span>
                                                    ${classroom.name}&nbsp;
                                            </span>
                    </c:forEach>
                </p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">考试科目</label>
            <div class="col-sm-8">
                <p class="form-control-static">${exam.subject.name}</p>
            </div>
        </div>

    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        试卷详情
    </div>

    <div class="panel-body">

        <div class="paper-exam-result">
            <div class="title-area">
                <div class="title"></div>
                <div class="comment"></div>
            </div>

            <div class="question-list">

            </div>
        </div>
    </div>
</div>

</div>

</div>