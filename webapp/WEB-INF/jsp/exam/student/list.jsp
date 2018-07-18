<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start content -->

<div class="row">
    <div class="col-lg-12">
        <h3>
            我的考试
        </h3>
        <div class="table-btns">
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
                'click .js-cancel': function (e, value, row, index) {
                    if (confirm("确定要取消本场考试吗？") == true)
                    {
                        myajax(
                            {
                                url: '/exam/operation/cancel.do?id=' + row.id,
                                success: function (res) {
                                    $("#table").bootstrapTable('refresh');
                                }
                            }
                        );
                    }
                },
                'click .js-add-teacher': function (e, value, row, index) {
                    window.location.href = "/exam/operation/addMarkTeacher?id=" + row.id;
                },
                'click .js-result': function (e, value, row, index) {
                    window.location.href = "/exam/student/result?id=" + row.id;
                },
                'click .js-review': function (e, value, row, index) {
                    window.location.href = "/exam/operation/review?id=" + row.id;
                },
                'click .js-delete': function (e, value, row, index) {
                    if (confirm("确定要取消本场考试吗？") == true)
                    {
                        myajax(
                            {
                                url: '/exam/manage/delete.do?id=' + row.id,
                                success: function (res) {
                                    $("#table").bootstrapTable('refresh');
                                }
                            }
                        );
                    }
                },'click .js-stop': function (e, value, row, index) {
                    if (confirm("确定要终止本场考试吗？") == true)
                    {
                        myajax(
                            {
                                url: '/exam/manage/stop.do?id=' + row.id,
                                success: function (res) {
                                    $("#table").bootstrapTable('refresh');
                                }
                            }
                        );
                    }
                },
                'click .js-mark': function (e, value, row, index) {
                    window.location.href = "/exam/operation/mark?id=" + row.id;
                },
                'click .js-submitMark': function (e, value, row, index) {
                    if (confirm("确定要提交批改结果吗？提交后不可更改") == true)
                    {
                        myajax(
                            {
                                url: '/exam/operation/submitMark.do?id=' + row.id,
                                success: function (res) {
                                    $("#table").bootstrapTable('refresh');
                                }
                            }
                        );
                    }
                },
                'click .js-enter': function (e, value, row, index) {
                    window.location.href = "/exam/student/enter?id=" + row.id;
                },
                'click .js-report': function (e, value, row, index) {
                    window.location.href = "/exam/operation/report?id=" + row.id;
                },
                'click .js-preview': function (e, value, row, index) {
                    window.location.href = "/exam/student/preview?id=" + row.id;
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
                            url: '/exam/student/list.do',
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
                                    title: '考试名称'
                                },
                                {

                                    field: 'openTime',
                                    title: '开始时间',
                                    formatter: function (value, row, index) {
                                        return TimeStampTDateTimeString(value);
                                        ;
                                    }

                                },
                                {
                                    field: 'duration',
                                    title: '考试时间',
                                    formatter: function (value, row, index) {
                                        if (value == "0")
                                        {
                                            return '不限';
                                        } else
                                        {
                                            return value;
                                        }
                                    }

                                },
                                {
                                    field: 'teacher',
                                    title: '发起人'
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
                                            '进行中': 'label-success',
                                            '已交卷': 'label-success',
                                            '答题中': 'label-danger',
                                            '阅卷中': 'label-danger',
                                            '已取消': 'label-warning',
                                            '已结束': 'label-info',
                                            '已过时': 'label-warning',
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
                                            'enter': '<span class="label label-action label-success js-enter">参加考试</span>',
                                            'result': '<span class="label label-action label-info  js-result">查看成绩</span>',
                                            'review': '<span class="label label-action label-danger text-danger js-review">审核</span>',


                                            'view': '<i class="fa fa-search js-view" title="预览"></i>',
                                            'edit': '<i class="fa fa-pencil js-edit" title="修改"></i>',
                                            'cancel': '<i class="fa fa-times js-cancel" title="取消"></i>',
                                            'addMarkTeacher': '<i class="fa fa-user-plus js-add-teacher" title="添加批卷教师"></i>',
                                            'delete': '<i class="fa fa-trash js-delete" title="删除"></i>',
                                            'mark': '<i class="fa fa-edit  js-mark" title="批阅"></i>',
                                            'submitMark': '<i class="fa fa-check js-submitMark" title="批阅提交"></i>',
                                            'preview': '<i class="fa fa-eye js-preview" title="查看概要"></i>',
                                            'stop': '<i class="fa fa-ban js-stop" title="终止考试"></i>',
                                            'report': '<i class="fa fa-bar-chart js-report" title="查看报告"></i>'

                                        };
                                        var html = '';
                                        for (var i = 0; i < act.length; i++)
                                        {
                                            if(dic[act[i]]==undefined)
                                            {
                                                continue;
                                            }
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
