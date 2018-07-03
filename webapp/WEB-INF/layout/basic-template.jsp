<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><tiles:getAsString name="title" ignore="true"/></title>
    <tiles:insertAttribute name="header" ignore="true"/>
    <tilesx:useAttribute id="list" name="additional-headers" classname="java.util.List" ignore="true" />
    <c:forEach var="item" items="${list}">
        <tiles:insertAttribute value="${item}" flush="true" />
    </c:forEach>
</head>
<body>

<div id="wrapper">
    <tiles:insertAttribute name="navbar" ignore="true"/>
<div class="app-container">
    <tiles:insertAttribute name="sidebar" ignore="true"/>
    <div class="side-visible-line" style="height: 100%;" data-side="collapse">
        <i class="fa fa-caret-left" style="padding-top: 250px;" ></i>
    </div>
    <div id="page-wrapper">
        <!-- start content -->
        <tiles:insertAttribute name="body" ignore="true"/>
        <!-- end content -->
</div>

    </div>
</div>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>

</html>






