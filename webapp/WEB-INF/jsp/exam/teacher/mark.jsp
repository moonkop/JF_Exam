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
    </div>

    <div class="paper-wrapper">

        <div class="paper-mark" style="margin-top: 50px">
            <div class="title-area">
                <div class="title"></div>
                <div class="comment"></div>
                <div class="question-list">





                </div>
            </div>

            <div class="navcard">
                <div class="navcard-title">
                    题目列表
                </div>

                <div class="navcard-workout-list">


                    <span id="workout-1">1</span><span id="workout-2">2</span><span id="workout-3">3</span><span
                        id="workout-4">4</span><span id="workout-5">5</span><span id="workout-6">6</span><span
                        id="workout-7">7</span><span id="workout-8">8</span><span id="workout-9">9</span><span
                        id="workout-10">10</span><span id="workout-11">11</span><span id="workout-12">12</span><span
                        id="workout-13">13</span><span id="workout-14">14</span><span id="workout-15">15</span><span
                        id="workout-16">16</span><span id="workout-17">17</span><span id="workout-18">18</span><span
                        id="workout-19">19</span><span id="workout-20">20</span>
                </div>
                <div class="navcard-title">
                    试卷导航
                </div>
                <div class="navcard-paper-list">
                    <span id="paper-1" class="navcard-active">1</span><span id="paper-2">2</span><span id="paper-3">3</span><span id="paper-4">4</span><span
                        id="paper-5">5</span><span id="paper-6">6</span><span id="paper-7">7</span><span
                        id="paper-8">8</span><span id="paper-9">9</span><span id="paper-10">10</span>
                </div>

                <div class="navcard-title">
                    试卷导航
                </div>

                <div class="navcard-paper-list navcard-paper-list-open">
                    <span id="paper--1">1</span><span id="paper--2">2</span><span id="paper--3">3</span><span
                        id="paper--4">4</span><span
                        id="paper--5" class="navcard-active">5</span><span id="paper--6">6</span><span id="paper--7">7</span><span
                        id="paper--8">8</span><span id="paper--9">9</span><span id="paper--10">10</span>
                </div>


            </div>
        </div>
    </div>
    <script src="/dist/js/question-manage.js"></script>
    <%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

    <script>


    </script>


</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>


</title>
</head>
<body>

</body>
