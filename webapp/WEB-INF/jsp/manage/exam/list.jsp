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
                    window.location.href = "/exam/manage/view?id=" + row.id;
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
                'click .js-addTea': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .js-viewScore': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
                },
                'click .judge': function (e, value, row, index) {
                    window.location.href = "#" + row.id;
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
                                        var dic = {
                                            '待审核': 'label-warning',
                                            '未开始': 'label-success',
                                            '待修改': 'label-warning',
                                            '开启考试': 'label-info',
                                            '进行中': 'label-danger',
                                            '阅卷中': 'label-danger',
                                            '取消': 'label-warning',
                                            '结束': 'label-success',
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
                                            'view': '<i class="fa fa-search js-view cursor" title="预览"></i>',
                                            'edit': '<i class="fa fa-pencil js-edit cursor " title="修改"></i>',
                                            'cancel': '<i class="fa fa-times js-cel  cursor" title="取消"></i>',
                                            'addMarkTeacher': '<i class="fa fa-user-plus  js-addTea      cursor" title="添加批卷教师"></i>',
                                            'viewScore': '<i class="fa fa-eye   js-viewScore   cursor" title="查看分数"></i>',
                                            'judge': '<i class="fa fa-eye   js-judge   cursor" title="审核"></i>',
                                            'delete': '<i class="fa fa-trash  js-delete   cursor" title="删除"></i>',
                                            'mark': '<i class="fa fa-edit   js-mark   cursor" title="批阅"></i>',
                                            'submitMark': '<i class="fa fa-check  js-submitMark  cursor" title="批阅提交"></i>',
                                            'attend': '<i class="fa fa-play   js-attend  cursor" title="参加考试"></i>',
                                            'viewBrief': '<i class="fa fa-eye  js-viewBrief  cursor" title="查看概要"></i>'
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
