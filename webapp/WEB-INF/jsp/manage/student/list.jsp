<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>学生管理</title>
    <%@include file="/WEB-INF/components/header.jsp"%>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp"%>
    <%@include file="/WEB-INF/components/sidebar.jsp"%>

    <div id="page-wrapper">
<!-- start content -->
<script>
    $(document).ready(
        function () {

            $(".js-view").on("click",function () {
                window.location.href="/dist/pages/manage/student/detail.html"
            })
            $(".js-edit").on("click",function () {
                window.location.href="/dist/pages/manage/student/edit.html"
            })
        }
    )
</script>

<div class="row">
    <div class="col-lg-12">
        <h3>
            学生管理
        </h3>
        <div class="table-btns">
            <a class="btn btn-primary" href="add.html"> 添加学生</a>
            <button class="btn btn-default"> 批量导入学生</button>
        </div>


        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>学校</th>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>机蜂夏令营</td>
                <td>215110</td>
                <td>张甜甜</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>机蜂夏令营</td>
                <td>215111</td>
                <td>刘丹丹</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>机蜂夏令营</td>
                <td>215112</td>
                <td>黄婷婷</td>
                <td>清华大学</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            </tbody>
        </table>


    </div>

</div>

<!-- end content -->
    </div>
</div>
<%@include file="/WEB-INF/components/footer.jsp"%>
</body>

</html>
