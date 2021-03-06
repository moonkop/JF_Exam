<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">
                <h3>
                    试卷管理
                </h3>
                <div class="table-btns">
                    <a class="btn btn-primary" href="/paper/add"> 添加试卷</a>
                </div>
                <script src="/vendor/bootstrap-table/bootstrap-table.js"></script>
                <script>


                    window.operateEvents = {
                        'click .js-edit': function (e, value, row, index) {
                            window.location.href = "/paper/edit?id=" + row.id;
                        },
                        'click .js-view': function (e, value, row, index) {
                            window.location.href = "/paper/detail?id=" + row.id;
                        },
                        'click .js-del': function (e, value, row, index) {
                            if(confirm("确定要删除吗?")==true){
                                $.ajax(
                                    {
                                        url: '/paper/delete.do?id=' + row.id,
                                        type: "post",
                                        success:function(res)
                                        {
                                            OnResult(res);
                                            $("#table").bootstrapTable('refresh');
                                        }
                                    }
                                )
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
                                    url: '/paper/list.do',
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
                                            field: 'title',
                                            title: '试卷名称',
                                        },
                                        {
                                            field: 'teacher',
                                            title: '出卷人',
                                        },
                                        {
                                            field: 'subject',
                                            title: '科目',
                                        },
                                        {
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
