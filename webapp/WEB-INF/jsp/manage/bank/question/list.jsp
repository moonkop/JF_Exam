<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->

<div class="app-header">
    <h3 style="display: inline-block;">题目管理</h3>
    <div class="" style="display: inline-block;margin-left: 30px;">
        <label>科目</label>
        <select id="select-subject" class="input-sm">
            <c:forEach items="${requestScope.subjects}" var="subject">
                <option value="${subject.id}"
                        <c:if test="${subjectSelected==subject.id}">selected</c:if>   >
                        ${subject.name}
                </option>
            </c:forEach>
        </select>

        <label>题目类型</label>
        <select id="select-question-type-filter" class="input-sm">
            <option value="0">全部</option>
            <option value="2">单选题</option>
            <option value="3">多选题</option>
            <option value="4">简答题</option>
        </select>

        <label>出题人</label>
        <select id="select-question-teacher-filter" class="input-sm">
            <option value="">全部教师</option>
            <c:forEach items="${requestScope.teachers}" var="teacher">
                <option value="${teacher.id}">
                        ${teacher.name}
                </option>
            </c:forEach>
        </select>

        <input type="checkbox" id="checkbox-question-only-mine-filter">
        <label>仅显示我的题目</label>

        <input type="checkbox" id="checkbox-question-recursive-filter">
        <label>显示子知识点题目</label>


        <button id="import" type="button" class="btn btn-default">导入题目</button>

    </div>
</div>
<div class="question-manage">

    <div id="jstree">
    </div>
    <div class="question-list">


    </div>
</div>


<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

<script src="/dist/js/question-manage.js"></script>
<script>
    var app={
        name:'question-manage',
        question_manage_actions:function(question, $question_panel)
        {
            $question_panel.find(".question-actions").append("" +
                "<i class='fa fa-search js-question-view' title='预览'></i>" +
                "<i class='fa fa-pencil js-question-edit' title='修改'></i>");
            if (User.role == 0 || User.id == question.createTeacher)
            {
                $question_panel.find(".question-actions").append("<i class='fa fa-trash js-question-delete' title='删除'></i>")
            }
        }
    }

    $(document).ready(function () {

        $("#import").on("click", function () {
            layer.open(
                {
                    type: 5,
                    closeBtn: 2,
                    title: '批量导入',
                    area: ['600x', '240px'],
                    content: $("#js-template-import").html()
                }
            )
        })
        $("#select-question-teacher-filter").on("change", question_manage_refresh_question_list);
        $("#select-question-type-filter").on("change", question_manage_refresh_question_list);
        $("#checkbox-question-only-mine-filter").on("change", question_manage_refresh_question_list);
        $("#checkbox-question-recursive-filter").on("change", question_manage_refresh_question_list);


        function initTree()
        {
            $('#jstree')
                .on("refresh.jstree", function (event, data) {
                    get_jstree().open_all();
                })
                .on("activate_node.jstree", function (event, data) {
                    question_manage_refresh_question_list();
                })
                .jstree({
                    'core': {
                        'worker': false,
                        "check_callback": true,
                        "themes": {"icons": false}
                    },
                    "plugins": [
                        "contextmenu",
                    ],

                    'contextmenu': {
                        'items': function (node) {
                            var tmp = {
                                create: {
                                    label: "添加题目",
                                    action: function (data) {
                                        question_add_on_click();
                                    }
                                }
                            };
                            return tmp;
                        }
                    }
                });

        }


        function getResourceTree()
        {
            $.ajax({
                url: '/manage/bank/topic/treeBySubject.do?subjectID=' + $("#select-subject").val(),
                success: function (res) {
                    OnResult(res, function () {
                            get_jstree().settings.core.data = res.payload.rows;
                            get_jstree().refresh();
                        }
                    )
                }
            })
        }

        initTree();
        getResourceTree();
        question_manage_bind_actions();
        $("#select-subject").on("change", function () {
            getResourceTree();

        })
    })




</script>

<!-- end content -->
