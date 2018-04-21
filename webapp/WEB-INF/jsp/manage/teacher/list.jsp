<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>教师管理</title>
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
                window.location.href="/dist/pages/manage/teacher/detail.html"
            })
            $(".js-edit").on("click",function () {
                window.location.href="/dist/pages/manage/teacher/edit.html"
            })
        }
    )
</script>

<div class="row">
    <div class="col-lg-12">
        <h3>
            教师管理
        </h3>
        <div class="table-btns">
            <a class="btn btn-primary" href="add.html"> 添加教师</a>
            <button class="btn btn-default"> 批量导入教师</button>
        </div>


        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>用户名</th>
                <th>真实姓名</th>
                <th>身份</th>
                <th>所属公司</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>YWD1997</td>

                <td>杨伟东</td>
                <td>管理员</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view" title="预览"></a>
                    <a class="fa fa-pencil js-edit" title="修改"></a>
                    <a class="fa fa-trash js-del" title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>caiji001</td>

                <td>夏岩</td>
                <td>教师</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view " title="预览"></a>
                    <a class="fa fa-pencil js-edit " title="修改"></a>
                    <a class="fa fa-trash js-del " title="删除"></a>
                </td>
            </tr>
            <tr>
                <td>luning</td>

                <td>鹿宁</td>
                <td>教师</td>
                <td>机蜂培训</td>
                <td>
                    <a class="fa fa-search js-view " title="预览"></a>
                    <a class="fa fa-pencil js-edit " title="修改"></a>
                    <a class="fa fa-trash js-del " title="删除"></a>
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
