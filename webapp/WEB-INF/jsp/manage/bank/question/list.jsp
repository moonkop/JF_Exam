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
    </div>
</div>
<div class="question-manage">

    <script type="text/javascript">
        // AJAX异步拉取数据
        var treeData = null;
        var jstree = null;

        function getJstree()
        {
            if (jstree == null)
            {
                return jstree = $.jstree.reference("#jstree");
            }
            return jstree;
        }

        $(document).ready(function () {

            function initTree()
            {
                $('#jstree')
                    .on("refresh.jstree", function (event, data) {
                        getJstree().open_all();
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
                                            var node = getJstree().get_node(data.reference);
                                            layer.open({
                                                type: 5,
                                                shadeClose: true, //点击遮罩关闭
                                                closeBtn: 2,
                                                title: '请输入题目名称',
                                                area: ['320px', '170px'],
                                                content: $("#js-add-form-template").html(),
                                                success: function () {
                                                    $("#form-add-topic>input[name='parent']").val(node.id);
                                                    $(".js-layer-form-add-btn").on("click", function () {
                                                            $.ajax(
                                                                {
                                                                    url: "/manage/bank/topic/create.do",
                                                                    data: getFormData("form-add-topic"),
                                                                    success: function (res) {
                                                                        OnResult(res);
                                                                        getResourceTree();
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    )

                                                },
                                                end: function () {
                                                }
                                            });
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
                                getJstree().settings.core.data = res.payload.rows;
                                getJstree().refresh();
                            }
                        )
                    }
                })
            }

            initTree();
            getResourceTree();
            $("#select-subject").on("change", function () {
                getResourceTree();

            })
        })


    </script>
    <div id="jstree">
    </div>
    <div class="question-list">


    </div>
</div>


<script id="js-template-question-panel" type="text/html">

    <div class="panel panel-primary panel-question">
        <div class="panel-body">
            <div class="question-header">
                    <span class="question-index">
                    </span>
                <span class="question-type">
                    </span>
                答案：
                <span class="question-answer">
                    </span>
            </div>
            <div class="question-body">
                    <span class="question-outline">
                    </span>
            </div>
        </div>
    </div>
</script>


<script id="js-template-question-option-list" type="text/html">
    <div class="question-option-list">
    </div>
</script>

<script id="js-template-question-option-radio" type="text/html">
    <div class="question-option question-option-radio">
        <input type="radio">
        <label></label>
    </div>
</script>

<script id="js-template-question-option-checkbox" type="text/html">
    <div class="question-option question-option-checkbox">
        <input type="checkbox">
        <label></label>
    </div>
</script>

<script>
    var questionList = [
        {
            id: "321321",
            outline: "世界上最好的语言是什么？",
            type: 2,
            code: "",
            options: [
                "JAVA",
                "Python",
                "PHP",
                "C++"
            ],
            score: 2,
            answer: "2"
        }
        , {
            id: "123123",
            outline: "面向过程的语言有",
            type: 3,
            code: "",
            options: [
                "Python",
                "C",
                "C#",
                "JAVA"
            ],
            score: 2,
            answer: "0,1"
        }
    ]
    var $question_panel = $("#js-template-question-panel");
    var $question_option_list = $("#js-template-question-option-list");
    var $question_option = $("#js-template-question-option-radio");
    var $question_option_checkbox = $("#js-template-question-option-checkbox");

    function QuestionTypeMap(str)
    {
        var QuestionTypeMap = {
            "2": "单选题",
            "3": "多选题",
            "4": "简答题"
        };
        return QuestionTypeMap[str];
    }

    function QuestionAnswerCharMap(char)
    {
        return String.fromCharCode(char.charCodeAt() + 1 + 16);
    }

    function QuestionAnswerNumsToChars(str)
    {
        var res = "";
        str.split(',').map(function (item) {
            res += QuestionAnswerCharMap(item);
        });
        return res;

    }

    function renderQuestion(question)
    {
        var $question_panel = $($("#js-template-question-panel").html());
        var $question_option_list = $($("#js-template-question-option-list").html());
        $question_panel.attr("id", question.id);
        $question_panel.find(".question-type").text(QuestionTypeMap(question.type));
        $question_panel.find(".question-index").text(question.id);
        $question_panel.find(".question-answer").text(QuestionAnswerNumsToChars(question.answer));
        $question_panel.find(".question-outline").text(question.outline);

        question.options.map(function (item, index) {
            switch (QuestionTypeMap(question.type))
            {
                case "单选题":
                    $question_option = $($("#js-template-question-option-radio").html());
                    break;
                case "多选题":
                    $question_option = $($("#js-template-question-option-checkbox").html());
                    break;
                case "简答题":
                    break;
            }
            $question_option.find("label").text(item);
            $question_option.find("input").attr("name","question_"+question.id);
            if (question.answer.indexOf(index) != -1)
            {
                $question_option.addClass("question-option-correct");
            }
            $question_option_list.append($question_option);
        })

        switch (QuestionTypeMap(question.type))
        {
            case "单选题":
            case "多选题":
                $question_panel.find(".question-body").append($question_option_list);
                break;
            case "简答题":
                break;
        }
        return $question_panel;
    }

    $(document).ready(function () {
        questionList.map(function (question) {
            $(".question-list").append(renderQuestion(question));
        })
    })

</script>

<script id="js-add-form-template" type="text/template">
    <div class="layer-popup">
        <form role="form" class="form-horizontal" id="form-add-topic">
            <input type="hidden" name="parent">
            <div class="form-group">
                <label class="col-sm-4 control-label">题干</label>
                <div class="col-sm-8">
                    <textarea type="text" class="form-control" id="inputID" name="name"/>
                </div>
            </div>
            <button type="button" class="js-layer-form-add-btn btn btn-primary  pull-right">
                <i class="glyphicon glyphicon-plus"></i>添加
            </button>
        </form>
    </div>
</script>
<!-- end content -->
