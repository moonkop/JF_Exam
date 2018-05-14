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
        <button id="import" type="button" class="btn">导入题目</button>
    </div>
</div>
<div class="question-manage">

    <div id="jstree">
    </div>
    <div class="question-list">


    </div>
</div>

<script id="js-template-import" type="text/html">


    <form action="/manage/bank/question/import.do" class="form-horizontal" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-4 control-label">您选择的科目</label>
            <div class="col-sm-8">
                <label>科目</label>
                <select class="input-sm" name="subjectId">
                    <c:forEach items="${requestScope.subjects}" var="subject">
                        <option value="${subject.id}"
                                <c:if test="${subjectSelected==subject.id}">selected</c:if>   >
                                ${subject.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">选择文件</label>
            <div class="col-sm-8">
                <input type="file" class="form-control" name="questionFile" data-field="file"/>
            </div>
        </div>
        <div class="form-group">

            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-8">
                <button class="form-control" type="submit"> 提交</button>
            </div>

        </div>
    </form>

</script>


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
                <div class="question-code">
                    <pre><code></code></pre>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="js-template-question-view" type="text/html">
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">题干</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="outline"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">代码</label>
            <div class="col-sm-9">
                <pre><code class="form-control-static" data-field="code"></code></pre>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">选项</label>
            <div class="col-sm-9">
                <div data-field="options"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">答案</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="answer"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">题目类型</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="type"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">所属科目</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="subject"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">所属知识点</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="topic"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">题目id</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="id"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">出题人</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="createTeacher"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">出题时间</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="createTime"></p>
            </div>
        </div>
    </div>
</script>


<script id="js-template-question-edit" type="text/html">
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">题干</label>
            <div class="col-sm-9">
                <textarea type="text" class="form-control" data-field="outline"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">代码</label>
            <div class="col-sm-9">
                <textarea class="form-control" data-field="code"></textarea>
            </div>
        </div>
        <div class="form-group-option">

        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-9">
                <input type="button" class="form-control btn btn-add-option" value="添加选项">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">题目类型</label>
            <div class="col-sm-9">
                <select name="type" id="select-question-type" class="form-control">
                    <option value="2">单选题</option>
                    <option value="3">多选题</option>
                    <option value="5">简答题</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属科目</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="subject"></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属知识点</label>
            <div class="col-sm-9">
                <p class="form-control-static" data-field="topic"></p>
            </div>
        </div>
        <div class="col-sm-offset-2">
            <button class="btn btn-primary js-edit-submit">提交</button>
        </div>
    </div>
</script>


<script id="js-tempalte-question-edit-option" type="text/html">
    <div class="form-group">
        <label class="col-sm-2 control-label">选项</label>
        <div class="col-sm-9">
            <div class="input-group">
           <span class="input-group-btn">
            <button class="btn js-valid" type="button">
                <i class="fa"></i>
            </button>
          </span>
                <input type="text" class="form-control" data-field="options" name="options[]">
                <span class="input-group-btn">
            <button class="btn btn-danger js-remove" type="button">
                <i class="fa fa-minus"></i>
            </button>
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

        $("#import").on("click", function () {
            layer.open(
                {
                    type: 5,
                    closeBtn: 2,
                    title: '批量导入',
                    area: ['400x', '200px'],
                    content: $("#js-template-import").html()
                }
            )
        })

        function initTree()
        {
            $('#jstree')
                .on("refresh.jstree", function (event, data) {
                    getJstree().open_all();
                })
                .on("activate_node.jstree", function (event, data) {
                    $.ajax(
                        {
                            url: '/manage/bank/question/list.do',
                            data: {
                                'topic.id': data.node.id,
                                pageSize: 1000,
                                offset: 0
                            },
                            success: function (res) {
                                OnResult(res, function () {
                                    var questionList = res.payload.rows;
                                    questionList.map(function (question) {
                                        if (question.options == null)
                                        {
                                            return;
                                        }

                                        question.options = JSON.parse(question.options);
                                    })
                                    $(".question-list").empty();
                                    questionList.map(function (question) {
                                        $(".question-list").append(renderQuestion(question));
                                        //详情
                                        $("#question_" + question.id + " .js-question-view").on("click", function () {
                                                getQuestionDetail(question.id, function (questionDetail) {
                                                    var $question_detail_form = RenderQuestion_View(questionDetail);
                                                    layer.open(
                                                        {
                                                            type: 5,
                                                            shadeClose: true, //点击遮罩关闭
                                                            closeBtn: 2,
                                                            title: '题目详情',
                                                            area: ['700px', '500px'],
                                                            content: $question_detail_form.prop("outerHTML"),
                                                            success: function () {
                                                                $(".layui-layer-content").addClass("layer-form");
                                                                $(".layui-layer-content pre code").each(function (index, obj) {
                                                                    hljs.highlightBlock(obj);
                                                                })
                                                            },
                                                            end: function () {
                                                            }
                                                        }
                                                    )
                                                })
                                            }
                                        )
                                        //编辑
                                        $("#question_" + question.id + " .js-question-edit").on("click", function () {
                                            getQuestionDetail(question.id, function (questionDetail) {
                                                var $question_edit_form = renderQuestion_Edit(questionDetail);
                                                layer.open(
                                                    {
                                                        type: 5,
                                                        shadeClose: true, //点击遮罩关闭
                                                        closeBtn: 2,
                                                        title: '题目编辑',
                                                        area: ['700px', '500px'],
                                                        content: $question_edit_form.prop("outerHTML"),
                                                        success: function () {
                                                            question_edit_option_status = {
                                                                optionid: 1,
                                                                optionCount: 1
                                                            }
                                                            if (questionDetail.options != null && questionDetail.options != "")
                                                            {
                                                                questionDetail.options = JSON.parse(questionDetail.options);
                                                            }

                                                            for(key in questionDetail.options)
                                                            {
                                                                var iscorrect = questionDetail.answer.indexOf(key)!=-1;

                                                                question_edit_on_add_option(questionDetail.options[key] ,iscorrect);
                                                            }

                                                            $(".layui-layer-content").addClass("layer-form");

                                                            //增加选项按钮点击
                                                            $(".btn-add-option").on("click", function () {
                                                                question_edit_on_add_option();
                                                            });

                                                        },
                                                        end: function () {

                                                        }
                                                    }
                                                )
                                            })
                                        });
                                    })
                                    $("pre code").each(function (index, obj) {
                                        hljs.highlightBlock(obj);
                                    })


                                })
                            }

                        }
                    )
                    data.node.id
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

        str.split("").map(function (item) {
            res += QuestionAnswerCharMap(item);
        });
        return res;

    }

    function getQuestionDetail(questionid, onsuccess)
    {
        $.ajax(
            {
                url: "/manage/bank/question/detail.do",
                data: {id: questionid},
                success: function (res) {
                    OnResult(res, function (res) {
                        var questionDetail = res.payload.object;
                        if (typeof onsuccess == "function")
                        {
                            onsuccess(questionDetail);
                        }
                    })
                }
            }
        )


    }

    function renderQuestionOptionList(question)
    {
        if (question.options == null || question.options == "")
        {
            return "";
        }
        var $question_option_list = $($("#js-template-question-option-list").html());
        for (var key in question.options)
        {

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
            $question_option.find("label").text(question.options[key]);
            $question_option.find("input").attr("name", "question_" + question.id);
            if (question.answer.indexOf(key) != -1)
            {
                $question_option.addClass("question-option-correct");
            }
            $question_option_list.append($question_option);
        }
        return $question_option_list;

    }

    function renderQuestion(question)
    {
        var $question_panel = $($("#js-template-question-panel").html());

        $question_panel.attr("id", "question_" + question.id);
        $question_panel.find(".question-type").text(QuestionTypeMap(question.type));
        $question_panel.find(".question-index").text(question.id);
        $question_panel.find(".question-actions").append("" +
            "<i class='fa fa-search js-question-view' title='预览'></i>" +
            "<i class='fa fa-pencil js-question-edit' title='修改'></i>");
        $question_panel.find(".question-outline").text(question.outline);
        if (question.code != null && question.code != "")
        {
            $question_panel.find(".question-code").css("display", "block");
            var code = $question_panel.find(".question-code code");
            code.text(question.code);
        }

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
            options: function (value, key) {
                if (questionDetail.options != null && questionDetail.options != "")
                {
                    questionDetail.options = JSON.parse(questionDetail.options);
                    return renderQuestionOptionList(questionDetail).prop("outerHTML");
                }
                return "";
            },
            answer: function (value, key) {
                return QuestionAnswerNumsToChars(value);
            },
            type: function (value, key) {
                return QuestionTypeMap(value);
            },
            createTime: function (value, key) {
                return new Date(value).toLocaleString();
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
            if (fieldContent == null || fieldContent == "")
            {
                $questionForm.find("[data-field='" + key + "']").parent().parent().hide();
            } else
            {
                $questionForm.find("[data-field='" + key + "']").html(fieldContent);

            }
        }
        return $questionForm;
    }


    function renderQuestion_Edit(questionDetail)
    {
        var $questionForm = $($("#js-template-question-edit").html());

        var config = {
            options: function (value, key) {

                return "";
            },
            answer: function (value, key) {
                return QuestionAnswerNumsToChars(value);
            },
            type: function (value, key) {
                return QuestionTypeMap(value);
            },
            createTime: function (value, key) {
                return new Date(value).toLocaleString();
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


            $questionForm.find("[data-field='" + key + "']").html(fieldContent);


        }
        return $questionForm;
    }

    var question_edit_option_status;

    function question_edit_on_add_option(content,iscorrect)
    {
        if (question_edit_option_status.optionCount > 9)
        {
            layer.msg("最多能添加9个选项");
            return;
        }
        question_edit_add_option(question_edit_option_status.optionid,content,iscorrect);
        question_edit_option_status.optionid++;
        question_edit_option_status.optionCount++;
    }

    function question_edit_add_option(optionid,content, iscorrect)
    {
        $option = $($("#js-tempalte-question-edit-option").html());
        $option.attr("id", "option-" + optionid);
        $(".form-group-option").append($option.prop("outerHTML"));
        if (iscorrect == true)
        {
            question_edit_option_toggle_correct(optionid, true);
        }
        //点击正确按钮事件
        $("#option-" + optionid + " input").val(content);
        $("#option-" + optionid + " .js-valid").on("click", function () {
            question_edit_option_toggle_correct(optionid);
        });
        //点击删除按钮事件
        $("#option-" + optionid + " .js-remove").on("click", function () {
            $("#option-" + optionid).remove();
            question_edit_option_status.optionCount--;
        })
    }

    function question_edit_option_toggle_correct(optionid, iscorrect)
    {
        $("#option-" + optionid).toggleClass("option-correct", iscorrect);
        $("#option-" + optionid + " .js-valid").toggleClass("btn-success", iscorrect).find(".fa").toggleClass("fa-check", iscorrect);


    }
    function question_edit_select_question_type(typeid)
    {
        $(".layui-layer-content #select-question-type").val(typeid);
    }


</script>

<!-- end content -->
