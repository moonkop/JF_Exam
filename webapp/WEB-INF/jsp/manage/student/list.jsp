<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">
                <h3>
                    学生管理
                </h3>
                <div class="table-btns">
                    <a class="btn btn-primary" href="/student/manage/edit"> 添加学生</a>
                    <%--<button class="btn btn-default" id="import"> 批量导入学生</button>--%>
                </div>
                <script src="/vendor/bootstrap-table/bootstrap-table.js"></script>
                <script>


                    window.operateEvents = {
                        'click .js-edit': function (e, value, row, index) {
                            window.location.href = "/student/manage/edit?id=" + row.id;
                        },
                        'click .js-view': function (e, value, row, index) {
                            window.location.href = "/student/manage/detail?id=" + row.id;
                        },
                        'click .js-del': function (e, value, row, index) {
                            if (confirm("确定要删除吗？") == true)
                            {
                                $.ajax(
                                    {
                                        url: '/student/manage/delete.do?id=' + row.id,
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
                                {  responseHandler:tableResponseHandler,
                                    locale: 'zh-CN',
                                    queryParams: queryParams,
                                    url: '/student/manage/list.do',
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
                                            field: 'school',
                                            title: '学校'
                                        },
                                        {
                                            field: 'classroom',
                                            title: '班级'

                                        },
                                        {
                                            field: 'name',
                                            title: '姓名'
                                        },
                                        {
                                            field: 'studentId',
                                            title: '学号'
                                        },
                                        {
                                            field: 'role',
                                            title: '身份'
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

                            $("#import").on("click", function () {
                                layer.open(
                                    {
                                        type: 5,
                                        closeBtn: 2,
                                        title: '批量导入学生',
                                        area: ['800x', '240px'],
                                        content: $("#js-template-import").html()
                                    }
                                )

                                function refreshClassroomSelect()
                                {
                                    $.ajax(
                                        {
                                            url: "/manage/getClassroomBySchoolID.do",
                                            data: {
                                                id: $("#selectSchool").val()
                                            },
                                            success: function (result) {
                                                $("#selectClassroom").empty();
                                                $.each(result, function (key, val) {
                                                    $("#selectClassroom").append(
                                                        '<option value=' + val.id + '>' + val.name + '</option>'
                                                    )
                                                });
                                            }
                                        }
                                    )
                                }

                                refreshClassroomSelect();
                                $("#selectSchool").on("change",function()
                                {
                                    refreshClassroomSelect();
                                })
                            })

                        }
                    )
                    ;
                </script>
                <table id="table"/>




            </div>

        </div>



<script id="js-template-import" type="text/html">
    <form action="/student/import.do" class="form-horizontal" method="post"
          enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-4 control-label">学校</label>
            <div class="col-sm-7">
                <select class="form-control" name="schoolId" id="selectSchool">
                    <c:forEach items="${schools}" var="school">
                        <option value="${school.id}">
                                ${school.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">班级</label>
            <div class="col-sm-7">

                <select class="form-control" name="classroomId" id="selectClassroom">
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">选择文件</label>
            <div class="col-sm-7">
                <input type="file" class="form-control" name="studentInfo" data-field="file"/>
            </div>
        </div>
        <div class="form-group">

            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-7">
                <button class="form-control" type="submit"> 提交</button>
            </div>

        </div>
    </form>
</script>

        <!-- end content -->
