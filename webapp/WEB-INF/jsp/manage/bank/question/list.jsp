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
    <div class="panel panel-question">
        <div class="panel-body">
            <div class="question-header">
                <span class="question-index">
                    </span>

                <span class="question-type">
                    </span>

                <span class="question-answer">
                    </span>

                <span class="question-value">
                    </span>

                <span class="question-actions">

                    </span>
            </div>
            <div class="question-body">
                    <span class="question-outline">
                    </span>
            </div>
        </div>
    </div>
</script>

<script id="js-template-question-view" type="text/html">
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">题干</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="outline"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">代码</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="codes"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">选项</label>
            <div class="col-sm-8">
                <div data-field="option"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">答案</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="answer"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">题目类型</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="type"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属科目</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="subject"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属知识点</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="topic"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">题目id</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="id"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">出题人</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="createTeacher"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">出题时间</label>
            <div class="col-sm-8">
                <p class="form-control-static" data-field="createTime"></p>
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
            id: "1de729a42212434a87b3771ee1acf717",
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
            id: "22c717969eb24fd187c7717a72425ad0",
            outline: "面向过程的语言有",
            type: 3,
            code: "",
            options: [
                "Python",
                "C",
                "C#",
                "JAVA"
            ],
            score: 3,
            answer: "0,1"
        }, {
            id: "2ff2af22be1e4276a2a899d3ae8563fc",
            outline: "JAVA有什么优点？",
            type: 4,
            code: "",
            options: [],
            score: 10
        }
    ]
    var $question_detail_form = $("#js-template-question-panel");
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

    function renderQuestionOptionList(question)
    {
        var $question_option_list = $($("#js-template-question-option-list").html());
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
            $question_option.find("input").attr("name", "question_" + question.id);
            if (question.answer.indexOf(index) != -1)
            {
                $question_option.addClass("question-option-correct");
            }
            $question_option_list.append($question_option);
        });
        return $question_option_list;

    }

    function renderQuestion(question)
    {
        var $question_panel = $($("#js-template-question-panel").html());

        $question_panel.attr("id", "question_" + question.id);
        $question_panel.find(".question-type").text(QuestionTypeMap(question.type));
        $question_panel.find(".question-index").text(question.id);
        $question_panel.find(".question-outline").text(question.outline);
        $question_panel.find(".question-actions").append("" +
            "<i class='fa fa-search js-question-view' title='预览'></i>" +
            "<i class='fa fa-pencil js-question-edit' title='修改'></i>");
        if (User.role == 0)
        {
            $question_panel.find(".question-actions").append("<i class='fa fa-trash js-question-del' title='删除'></i>")
        }

        switch (QuestionTypeMap(question.type))
        {
            case "单选题":
            case "多选题":
                $question_panel.find(".question-body").append(renderQuestionOptionList(question));
                $question_panel.find(".question-answer").text("答案：" + QuestionAnswerNumsToChars(question.answer));
                break;
            case "简答题":
                break;
        }
        return $question_panel;
    }

    function RenderQuestion_View(questionDetail)
    {
        var $questionForm = $($("#js-template-question-view").html());
        var config = {
            option: function (value,key) {
               // return renderQuestionOptionList(value).prop("outerHTML");
            }
        }

        var fieldContent;
        for (var key in questionDetail)
        {
            var currentConfig = config[key];
            if (currentConfig != undefined)
            {
                if (typeof currentConfig == 'function')
                {
                    fieldContent = currentConfig(questionDetail[key], key);
                }
            } else
            {
                fieldContent = questionDetail[key];
            }
            $questionForm.find("[data-field='" + key+"']").html(fieldContent);
        }
        return $questionForm;
    }


    $(document).ready(function () {
        questionList.map(function (question) {
            $(".question-list").append(renderQuestion(question));
            $("#question_" + question.id + " .js-question-view").on("click",
                function () {
                    $.ajax(
                        {
                            url:"/manage/bank/question/detail.do",
                            data:{id:question.id},
                            success:function (res) {
                                OnResult(res,function (res) {
                                    var questionDetail=res.payload.object;
                                    $question_detail_form = RenderQuestion_View(questionDetail);
                                    layer.open(
                                        {
                                            type: 5,
                                            shadeClose: true, //点击遮罩关闭
                                            closeBtn: 2,
                                            title: '题目详情',
                                            area: ['700px', '500px'],
                                            content: $question_detail_form.prop("outerHTML"),
                                            success: function () {

                                            },
                                            end: function () {
                                            }
                                        }
                                    )
                                })
                            }
                        }
                    )
                }
            )
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
