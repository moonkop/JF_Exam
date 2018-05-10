<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title" ignore="true"/></title>
    <tiles:insertAttribute name="header" ignore="true"/>
    <tiles:insertAttribute name="additional-headers" ignore="true"/>
</head>
<body>

<div id="wrapper">
    <tiles:insertAttribute name="navbar" ignore="true"/>
<div class="app-container">
    <tiles:insertAttribute name="sidebar" ignore="true"/>
    <div class="side-visible-line hidden-xs" style="height: 100%;" data-side="collapse">
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






