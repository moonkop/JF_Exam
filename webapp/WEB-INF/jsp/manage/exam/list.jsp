<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->

<div class="row">
    <div class="col-lg-12">
        <h3>
            考试管理
        </h3>
        <div class="table-btns">
            <a class="btn btn-primary" href="#"> 添加考试</a>
        </div>
        <script src="/vendor/bootstrap-table/bootstrap-table.js"></script>
        <script>


            window.operateEvents = {
                'click .js-edit': function (e, value, row, index) {
                    window.location.href = "/exam/manage/edit?id=" + row.id;
                },
                'click .js-view': function (e, value, row, index) {
                    window.location.href = "/exam/manage/detail?id=" + row.id;
                },
                'click .js-cel': function (e, value, row, index) {
                    if (confirm("确定要取消本场考试吗？") == true)
                    {
                        $.ajax(
                            {
                                url: '/exam/operation/cancel?id=' + row.id,
                                type: "post",
                                success: function (res) {
                                    OnResult(res);
                                    $("#table").bootstrapTable('refresh');
                                }
                            }
                        );

                    }
                }
            };


            $(document).ready(
                function () {

                    var $table;

                    $table = $("#table");
                    $table.bootstrapTable(
                        {
                            responseHandler: tableResponseHandler,
                            locale: 'zh-CN',
                            queryParams: queryParams,
                            url: '/exam/manage/list.do',
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
                                    title: '试卷'
                                },
                                {

                                    field: 'openTime',
                                    title: '开始时间',
                                    formatter: function (value, row, index) {
                                        var date = new Date(value);
                                        var temp;
                                        temp = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay() + " "
                                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                                        return temp;
                                    }

                                },
                                {
                                    field: 'duration',
                                    title: '考试时间'
                                },
                                {
                                    field: 'teacher',
                                    title: '出卷人'
                                },
                                {
                                    field: 'subject',
                                    title: '科目'
                                },
                                {
                                    field: 'examStatusView',
                                    title: '状态',
                                    formatter: function (value, row, index) {
                                        var temp = value;
                                        var html = "";
                                        if (temp == "待审核")
                                        {
                                            html = '<span class="label label-danger">待审核</span>\n';
                                        }
                                        if(temp=="未开始"){
                                            html ='<span class="label label-warning">未开始</span>\n';
                                        }
                                        if(temp=="待修改"){
                                            html ='<span class="label label-warning">待修改</span>\n';
                                        }
                                        if(temp=="开启考试"){
                                            html ='<span class="label label-warning">开启考试</span>\n';
                                        }
                                        if(temp=="进行中"){
                                            html ='<span class="label label-warning">进行中</span>\n';
                                        }
                                        if(temp=="阅卷中"){
                                            html ='<span class="label label-warning">阅卷中</span>\n';
                                        }
                                        if(temp=="取消"){
                                            html ='<span class="label label-warning">取消</span>\n';
                                        }
                                        if(temp=="结束"){
                                            html ='<span class="label label-warning">结束</span>\n';
                                        }
                                        return html;
                                    }


                                },
                                {
                                    field: 'action',
                                    title: '操作',
                                    events: operateEvents,
                                    formatter: function (value, row, index) {
                                        var html = '';

                                        html += '<i class="fa fa-search del js-view cursor" title="预览"></i>';
                                        html += '<i class="fa fa-pencil edit js-edit cursor " title="修改"></i>';
                                        html += '<i class="fa fa-times cancel js-cel cursor" title="取消"></i>';
                                        html += '<i class="fa fa-plus js-add cursor" title="添加批卷教师"></i>';
                                        return html;
                                    }
                                }
                            ]
                        }
                    );
                }
            )
            ;
        </script>
        <table id="table"/>
    </div>

</div>

<!-- end content -->
