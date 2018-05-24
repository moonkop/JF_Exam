<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->
<div style="display: flex;">
    <div class="question-pick">
        <div style="margin: 10px">
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
            <div style="display: inline-block;">
                <input type="checkbox" id="checkbox-question-only-mine-filter">
                <label>仅显示我的题目</label>
            </div>
            <div style="display: inline-block;">

                <input type="checkbox" id="checkbox-question-recursive-filter">
                <label>显示子知识点题目</label>
            </div>


        </div>
        <div class="question-manage">

            <div id="jstree">
            </div>
            <div class="question-list">
            </div>
        </div>
    </div>


    <div class="paper-edit">f
        <div style="margin: 10px" class="text-right">


            <button class="btn btn-primary" id="js-btn-edit-title">
                编辑标题
            </button>

            <button class="btn btn-primary">
                设置分值
            </button>

            <button class="btn btn-primary" id="js-btn-submit">
                提交修改
            </button>


        </div>
        <div class="title-area">
            <div class="title"></div>
            <div class="comment"></div>
        </div>

        <div class="question-list">

        </div>


    </div>
</div>


<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>
<script src="/dist/js/question-manage.js"></script>
<script>

    var app = {
        name: 'paper-edit',
        question_manage_actions: function (question, $question_panel) {
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
            id: '${paper.id}',
            title: '${paper.title}',
            comment: '${paper.comment}',
            questionList:${questionList},
        }
    };


    $(document).ready(
        function () {
            $("#select-question-teacher-filter").on("change", question_manage_refresh_question_list);
            $("#select-question-type-filter").on("change", question_manage_refresh_question_list);
            $("#checkbox-question-only-mine-filter").on("change", question_manage_refresh_question_list);
            $("#checkbox-question-recursive-filter").on("change", question_manage_refresh_question_list);
            $("#js-btn-edit-title").on("click", function () {
                paper_edit_on_edit_title();
            })
            $("#js-btn-submit").on("click", function () {
                paper_edit_on_submit()
            })
            $(".side-visible-line").click();

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
                                    },
                                    addToPaper: {
                                        label: '批量添加到试卷',
                                        action: function (data) {
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
            render_paper_title();
            paper_edit_render_question_list();
            question_manage_bind_actions();

            $(".paper-edit .question-list").on("click", ".js-question-move-up", function (sender) {
                var question = app.paper.questionList[$(this).parents('.panel-question').attr("data-index")];
                move_up(app.paper.questionList, question.index);
                paper_edit_render_question_list();
            });
            $(".paper-edit .question-list").on("click", ".js-question-move-down", function (sender) {
                var question = app.paper.questionList[$(this).parents('.panel-question').attr("data-index")];
                move_down(app.paper.questionList, question.index);
                paper_edit_render_question_list();
            });
            $(".paper-edit .question-list").on("click", ".js-question-view", function (sender) {
                var question = app.paper.questionList[$(this).parents('.panel-question').attr("data-index")];
            });
            $(".paper-edit .question-list").on("click", ".js-question-delete", function (sender) {
                var question = app.paper.questionList[$(this).parents('.panel-question').attr("data-index")];
                app.paper.questionList.splice(question.index, 1);
                paper_edit_render_question_list();
                layer.msg('删除成功');
            });
            $(".paper-edit .question-list").on("click", ".js-question-edit", function (sender) {
                var question = app.paper.questionList[$(this).parents('.panel-question').attr("data-index")];
                app.currentLayerIndex = layer.open(
                    {
                        type: 5,
                        shadeClose: true, //点击遮罩关闭
                        closeBtn: 2,
                        title: '题目编辑',
                        area: ['800px', '600px'],
                        content: "",
                        success: function (a) {
                            a.find('.layui-layer-content').append(paper_edit_render_question_edit(question));
                        },
                        end: function () {

                        }
                    }
                )
            });
        }
    )

    // 交换数组元素
    var swapItems = function (arr, index1, index2) {
        arr[index1] = arr.splice(index2, 1, arr[index1])[0];
        return arr;
    };
    // 上移
    move_up = function (arr, $index) {
        if ($index == 0)
        {
            return;
        }
        swapItems(arr, $index, $index - 1);
    };
    // 下移
    move_down = function (arr, $index) {
        if ($index == arr.length - 1)
        {
            return;
        }
        swapItems(arr, $index, $index + 1);
    };
    function getResourceTree(subjectid)
    {
        $.ajax({
            url: '/manage/bank/topic/treeBySubject.do?subjectID=' + subjectid,
            success: function (res) {
                OnResult(res, function () {
                        get_jstree().settings.core.data = res.payload.rows;
                        get_jstree().refresh();
                    }
                )
            }
        })
    }



</script>

