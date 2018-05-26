<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>

</script>

<div class="row">
    <div class="col-lg-12">
        <h3>
            考试详情
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">考试id</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.id}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">考试名称</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.remark}</p>
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
                            <c:if test="${exam.duration!=0}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">答题时间</label>
                                    <div class="col-sm-8">
                                        <p class="form-control-static" >${exam.duration}分钟</p>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${exam.openDuration!=0}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">入场时间</label>
                                    <div class="col-sm-8">
                                        <p class="form-control-static" >${exam.openDuration}分钟</p>
                                    </div>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">发起者</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.createTeacher.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">考试科目</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.subject.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">考试名称</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${exam.name}</p>
                                </div>
                            </div>

                            <div class="col-sm-offset-2">
                            <a class="btn btn-default" href="/exam/manage/edit?id=${exam.id}">编辑</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">


                </div>
            </div>
        </div>
        <div class="panel">
            <div class="panel-body">
                <div class="paper-view">
                    <div class="title-area">
                        <div class="title"></div>
                        <div class="comment"></div>
                    </div>

                    <div class="question-list">

                    </div>
                </div>
            </div>
        </div>
        <div class="panel">
            <div class="panel-body">
                <div class="text-right">
                    <button class="btn btn-default js-back">
                        返回
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/dist/js/question-manage.js"></script>
<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

<script>
    var app = {
        name: 'paper-edit',
        paper: {
            id: '${paper.id}',
            title: '${paper.title}',
            comment: '${paper.comment}',
            questionList:${questionList},
        }
    };
    $(document).ready(function () {
        paper_view_render_question_list();
        render_paper_title();
        $("#openTime").text(TimeStampTDateTimeString(${exam.openTime}));
        $("#closeTime").text(TimeStampTDateTimeString(${exam.closeTime}));

    })
</script>