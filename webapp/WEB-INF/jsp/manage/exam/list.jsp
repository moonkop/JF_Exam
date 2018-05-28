<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->

<div class="row">
    <div class="col-lg-12">
        <h3>
            考试管理
        </h3>
        <div class="table-btns">
            <a class="btn btn-primary" href="/exam/manage/edit"> 添加考试</a>
        </div>
        <script src="/vendor/bootstrap-table/bootstrap-table.js"></script>
        <script>

            window.operateEvents = {
                'click .js-view': function (e, value, row, index) {
                    window.location.href = "/exam/manage/detail?id=" + row.id;
                },
                'click .js-edit': function (e, value, row, index) {
                    window.location.href = "/exam/manage/edit?id=" + row.id;
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
                },
                'click .js-add-teacher': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-viewScore': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-judge': function (e, value, row, index) {
                    window.location.href = "/exam/operation/review?id=" + row.id;
                },
                'click .js-delete': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-mark': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-submitMark': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-attend': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-viewBrief': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },

            }

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
                                        return date.toLocaleString();
                                        ;
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
                                        var dic = {
                                            '待审核': 'label-warning',
                                            '未开始': 'label-info',
                                            '待修改': 'label-danger',
                                            '进行中': 'label-info',
                                            '答题中': 'label-danger',
                                            '阅卷中': 'label-danger',
                                            '已取消': 'label-warning',
                                            '已结束': 'label-success',
                                        }
                                        var html = "<span class='label " + dic[temp] + "'>" + temp + "</span>";
                                        return html;
                                    }
                                },
                                {
                                    field: 'operation',
                                    title: '操作',
                                    events: operateEvents,
                                    formatter: function (value, row, index) {
                                        var act = value;
                                        var dic = {
                                            'judge': '<span class="label label-action label-danger text-danger js-judge">审核</span>',
                                            'view': '<i class="fa fa-search js-view" title="预览"></i>',
                                            'edit': '<i class="fa fa-pencil js-edit" title="修改"></i>',
                                            'cancel': '<i class="fa fa-times js-cel" title="取消"></i>',
                                            'addMarkTeacher': '<i class="fa fa-user-plus js-add-teacher" title="添加批卷教师"></i>',
                                            'viewScore': '<i class="fa fa-eye  js-viewScore" title="查看分数"></i>',
                                            'delete': '<i class="fa fa-trash js-delete" title="删除"></i>',
                                            'mark': '<i class="fa fa-edit  js-mark" title="批阅"></i>',
                                            'submitMark': '<i class="fa fa-check js-submitMark" title="批阅提交"></i>',
                                            'attend': '<i class="fa fa-play  js-attend" title="参加考试"></i>',
                                            'viewBrief': '<i class="fa fa-eye js-viewBrief" title="查看概要"></i>'
                                        };
                                        var html = '';
                                        for (var i = 0; i < act.length; i++)
                                        {
                                            html += dic[act[i]];
                                        }
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
