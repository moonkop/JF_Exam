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
        <button class="btn btn-default"> 切换视图</button>
        <button class="btn btn-danger pull-right"> 提交试卷</button>
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
                id: '${exam.id}'
            },
            paper: {
                id: '${paper.id}',
                title: '${paper.title}',
                comment: '${paper.comment}',
                questionList:${questionList},
            }
        }

        $(document).ready(function () {
            $(".side-visible-line").click();
            render_paper_title();
            paper_exam_render_question_list();
        });

    </script>


</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>






