<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>

</script>

<div class="row">
    <div class="col-lg-12">
        <h3>
            考试概要
        </h3>

            <div class="panel panel-primary main-panel">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">考试名称</label>
                                    <div class="col-sm-8">
                                        <p class="form-control-static">${exam.name}</p>
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
                                            <p class="form-control-static">${exam.duration}分钟</p>
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${exam.openDuration!=0}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">入场时间</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${exam.openDuration}分钟</p>
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
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">考试名称</label>
                                    <div class="col-sm-8">
                                        <p class="form-control-static">${exam.name}</p>
                                    </div>
                                </div>


                            </div>
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
    <script>
        var app = {
            name: 'exam-preview',
            exam:{
                id:'${exam.id}',
                studentExam:{
                    id:"${studentExam.id}"
                }
            }

        };
        $(document).ready(function () {
            $("#openTime").text(TimeStampTDateTimeString(${exam.openTime}));
            $("#closeTime").text(TimeStampTDateTimeString(${exam.closeTime}));

        })
    </script>