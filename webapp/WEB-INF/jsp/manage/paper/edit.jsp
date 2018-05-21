<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->
<div style="display: flex;">
<div class="question-pick" style="height: 100%; width: 55%;">
    <div style="margin-top: 10px">
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

    </div>
    <div class="question-manage" style="">

        <div id="jstree">
        </div>
        <div class="question-list">
        </div>
    </div>
</div>


<div class="paper-edit" style="height: 100%; width: 45%">
    <h3>这是一张试卷</h3>
    <div><span>注意事项：</span><span>在填写本张试卷时，请不要使用铅笔，水笔，钢笔，圆珠笔，毛笔，蜡笔，水彩笔，粉笔等</span></div>

<div class="paper-question-list">

</div>


</div>
</div>



<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>
<script src="/dist/js/question-manage.js"></script>
<script>

    var app = {
        name: 'paper-edit',
        render_question_actions:function(question,$question_panel)
        {
            $question_panel.find(".question-actions").append("" +
                "<i class='fa fa-plus js-question-add-to-paper' title='添加到试卷'></i>" +
                "<i class='fa fa-search js-question-view' title='预览'></i>" +
                "<i class='fa fa-pencil js-question-edit' title='修改'></i>");
            if (User.role == 0 || User.id == question.createTeacher)
            {
                $question_panel.find(".question-actions").append("<i class='fa fa-trash js-question-delete' title='删除'></i>")
            }
        },
        paper: {
            name: '这是一张试卷',
            remark: '不要作弊',
            paperContent: [
                {
                    id: '1c55323ef7cb4f6ca616e768b38b99d3',
                    outline: '下面的哪些声明是合法的？（ ）',
                    options: {"0": "long 1 = 499", "1": "int i = 4L", "2": "float f =1.1", "3": "char d = 34.4"},
                    index: '1',
                    score: '3'
                }
            ],
        }
    };

    $(document).ready(
        function()
        {
            $("#select-question-teacher-filter").on("change", on_filter_change);
            $("#select-question-type-filter").on("change", on_filter_change);
            $("#checkbox-question-only-mine-filter").on("change", on_filter_change);
            $("#checkbox-question-recursive-filter").on("change", on_filter_change);


            function initTree()
            {
                $('#jstree')
                    .on("refresh.jstree", function (event, data) {
                        get_jstree().open_all();
                    })
                    .on("activate_node.jstree", function (event, data) {
                        on_filter_change();
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
                                    },
                                    addToPaper:{
                                        label:'批量添加到试卷',
                                        action:function (data) {
                                            question_add_to_paper();
                                        }
                                    }
                                };
                                return tmp;
                            }
                        }
                    });

            }



            initTree();
            getResourceTree(9);

        }
    )
    function getResourceTree(subjectid)
    {
        $.ajax({
            url: '/manage/bank/topic/treeBySubject.do?subjectID='+subjectid ,
            success: function (res) {
                OnResult(res, function () {
                        get_jstree().settings.core.data = res.payload.rows;
                        get_jstree().refresh();
                    }
                )
            }
        })
    }

    function question_add_to_paper()
    {

    }
    function renderPaper(paper)
    {

    }

</script>

