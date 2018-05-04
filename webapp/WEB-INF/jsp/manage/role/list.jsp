<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>角色管理</title>
    <%@include file="/WEB-INF/components/header.jsp" %>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp" %>
    <%@include file="/WEB-INF/components/sidebar.jsp" %>

    <div id="page-wrapper">
        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">
                <h3>
                    角色管理
                </h3>
                <div class="table-btns">
                    <a class="btn btn-primary" href="/manage/role/edit"> 添加角色</a>
                </div>
                <script src="/vendor/bootstrap-table/bootstrap-table.js"></script>
                <script>


                    window.operateEvents = {
                        'click .js-edit': function (e, value, row, index) {
                            window.location.href = "/manage/role/edit?id=" + row.id;
                        },
                        'click .js-view': function (e, value, row, index) {
                            window.location.href = "/manage/role/detail?id=" + row.id;
                        },
                        'click .js-del': function (e, value, row, index) {
                            if (confirm("确定要删除吗？") == true)
                            {
                                window.location.href = "/manage/role/delete.do?id=" + row.id;
                                // $.ajax(
                                //     {
                                //         url: '/manage/role/delete.do?id=' + row.id,
                                //         type: "post",
                                //         success:function(res)
                                //         {
                                //             OnResult(res);
                                //             $("#table").bootstrapTable('refresh');
                                //         }
                                //     }
                                // );
                            }
                        }
                    };


                    $(document).ready(
                        function () {

                            var $table;

                            $table = $("#table");
                            $table.bootstrapTable(
                                {
                                    queryParams: queryParams,
                                    responseHandler:tableResponseHandler,
                                    locale: 'zh-CN',
                                    url: '/manage/role/list.do',
                                    method: 'get',
                                    cache: false,
                                    pagination: true,
                                    sidePagination: "server",
                                    pageNumber: 1,
                                    pageSize: 10,                       //每页的记录行数（*）
                                    pageList: [10, 25, 50, 100],
                                    columns: [
                                        {
                                            field: 'id',
                                            title: '序号',
                                            visible: false,
                                        },
                                        {
                                            field: 'name',
                                            title: '角色名称',
                                        }, {
                                            field: 'action',
                                            title: '操作',

                                            events: operateEvents,
                                            formatter: function (value, row, index) {
                                                var html = '';

                                                html += '<i class="fa fa-search del js-view cursor" title="预览"></i>';
                                                html += '<i class="fa fa-pencil edit js-edit cursor " title="修改"></i>';
                                                html += '<i class="fa fa-trash del js-del cursor" title="删除"></i>';

                                                return html;
                                            }
                                        }
                                    ]
                                }
                            );
                        }
                    );
                </script>

                <table id="table">

                </table>


            </div>

        </div>

        <!-- end content -->
    </div>
</div>
<%@include file="/WEB-INF/components/footer.jsp" %>
</body>

</html>
