var treeData = null;
var jstree = null;
var editLayerIndex = null;
var viewLayerIndex = null;


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
    str.split(",").map(function (item) {
        res += QuestionAnswerCharMap(item);
    });
    return res;
}

function to_answer_array(str)
{
    return str.split(',').map(function (item) {
        return item;
    });
}

function question_option_parse_json(question)
{
    if (question.options == "" || question.options == null || typeof question.options != "string")
    {
        return question;
    }
    question.options = JSON.parse(question.options);
    return question;
}

function get_jstree()
{
    if (jstree == null)
    {
        return jstree = $.jstree.reference("#jstree");
    }
    return jstree;
}

//获取题目详细信息
function get_question_detail(questionid, onsuccess)
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

function get_question_list()
{

}

var $question_detail_form = $("#js-template-question-panel");
var $question_option_list = $("#js-template-question-option-list");
var $question_option = $("#js-template-question-option-radio");
var $question_option_checkbox = $("#js-template-question-option-checkbox");


function question_manage_refresh_question_list()
{
    if (get_jstree() == null)
    {
        return;
    }

    $.ajax(
        {
            url: '/manage/bank/question/list.do',
            data: {
                _topicIds: get_jstree().get_selected(),
                pageSize: 1000,
                offset: 0,
                recursive: $("#checkbox-question-recursive-filter")[0].checked,
                showMe: $("#checkbox-question-only-mine-filter")[0].checked,
                'teacher.id': $("#select-question-teacher-filter").val(),
                'questionType.id': $("#select-question-type-filter").val()
            },
            success: function (res) {
                OnResult(res, function () {
                    app['questionList'] = res.payload.rows;
                    question_manage_render_question_list(app['questionList']);
                })
            }

        }
    )
}

function question_add_on_click()
{
    var topicid = get_jstree().get_selected();

    app.currentLayerIndex = layer.open(
        {
            type: 5,
            shadeClose: true, //点击遮罩关闭
            closeBtn: 2,
            title: '题目详情',
            area: ['800px', '600px'],
            content: "",
            success: function (a) {
                a.find('.layui-layer-content').append(question_manage_render_question_edit({topicid: topicid}));
            },
            end: function () {
            }
        }
    )
}


function paper_add_question(questionid)
{
    get_question_detail(questionid, function (questionDetail) {
            var question = question_detail_to_paper_question(questionDetail);
            app.paper.questionList.push(question);
            paper_edit_render_question_list();
        }
    )
}

function paper_question_set_index(question_list)
{
    for (var i = 0, len = question_list.length; i < len; i++)
    {
        question_list[i]['index'] = i;
    }
}

//将详细的题目仅保留答案等必要信息
function question_detail_to_paper_question(questionDetail)
{
    question_option_parse_json(questionDetail);

    var question = {
        outline: questionDetail.outline,
        id: questionDetail.id,
        options: questionDetail.options,
        answer: questionDetail.answer,
        code: questionDetail.code,
        type: questionDetail.type,
        value: questionDetail.value,
    };
    return question;
}


function question_manage_bind_actions()
{

    function question_manage_question_edit_on_click(questionID)
    {

        get_question_detail(questionID, function (questionDetail) {
            app.currentLayerIndex = layer.open(
                {
                    type: 5,
                    shadeClose: true, //点击遮罩关闭
                    closeBtn: 2,
                    title: '题目编辑',
                    area: ['800px', '600px'],
                    content: "",
                    success: function (a) {
                        a.find('.layui-layer-content').append(question_manage_render_question_edit(questionDetail));
                    },
                    end: function () {

                    }
                }
            )
        })

    }

    function question_manage_question_view_on_click(questionID)
    {
        get_question_detail(questionID, function (questionDetail) {
            var $question_detail_form = question_manage_render_view(questionDetail);
            app.currentLayerIndex = layer.open(
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

    function question_manage_question_add_to_paper_on_click(questionid)
    {
        for (var i = 0, len = app.paper.questionList.length; i < len; i++)
        {
            if (app.paper.questionList[i].id == questionid)
            {
                layer.confirm("您已添加过该题，是否继续添加", function () {
                    paper_add_question(questionid);
                    layer.msg('添加成功');
                });
                return;
            }
        }
        paper_add_question(questionid);
        layer.msg('添加成功');
    }

    $(".question-manage .question-list").on("click", ".js-question-view", function () {
        var id = $(this).parents('.panel-question').attr("data-id");
        question_manage_question_view_on_click(id);
    })
    $(".question-manage .question-list").on("click", ".js-question-edit", function () {
        var id = $(this).parents('.panel-question').attr("data-id");
        question_manage_question_edit_on_click(id);
    })
    $(".question-manage .question-list").on("click", ".js-question-add-to-paper", function () {
        var id = $(this).parents('.panel-question').attr("data-id");
        question_manage_question_add_to_paper_on_click(id);
    })
    $(".question-manage .question-list").on("click", ".js-question-delete", function () {
        var id = $(this).parents('.panel-question').attr("data-id");
        layer.confirm("你确定要删除吗？", function () {
            $.ajax(
                {
                    url: '/manage/bank/question/delete.do',
                    data: {id: id},
                    success: function (res) {
                        OnResult(res, function () {
                            layer.msg('删除成功');
                            question_manage_refresh_question_list();
                        })
                    }
                }
            )
        })

    })
}

//通用题目列表渲染
function render_question_list(question_list, $question_list, question_render_function)
{
    question_list.map(function (question) {
        question_option_parse_json(question);
    });
    $question_list.empty();
    question_list.map(function (question) {
        $question_list.append(question_render_function(question));
    });
    $("pre code").each(function (index, obj) {
        hljs.highlightBlock(obj);
    })
}

function get_basic_question_panel(question)
{
    var $question_panel = $($("#js-template-question-panel").html());
    render_question_basics($question_panel, question);
    render_question_code_if_needed($question_panel, question);
    render_question_options_if_needed($question_panel, question);
    return $question_panel;
}

function render_question_basics($question_panel, question)
{
    $question_panel.attr("id", "paper_question_" + question.index);
    $question_panel.attr("data-index", question.index);
    $question_panel.attr("data-type", question.type);
    $question_panel.attr("data-id", question.id);
    $question_panel.find(".question-type").text(QuestionTypeMap(question.type));
    $question_panel.find(".question-index").text(question.index + 1 + ". ");
    $question_panel.find(".question-outline").text(question.outline);
    if (question.value != undefined && question.value != null)
    {
        $question_panel.find(".question-value").text(question.value + "分");
    }
}

function render_question_code_if_needed($question_panel, question)
{
    if (question.code != null && question.code != "")
    {
        $question_panel.find(".question-code").css("display", "block");
        var code = $question_panel.find(".question-code code");
        code.text(question.code);
    }
}

function render_question_options_if_needed($question_panel, question)
{
    if (['单选题', '多选题'].indexOf(QuestionTypeMap(question.type)) != -1)
    {
        $question_panel.find(".question-body").append(render_question_option_list(question));
    }
}


//题目编辑部分列表渲染
function question_manage_render_question_list(question_list)
{
    //在列表中显示一个题目
    function question_manage_render_question(question)
    {
        var $question_panel = get_basic_question_panel(question);
        if (typeof app.question_manage_actions == 'function')
        {
            app.question_manage_actions(question, $question_panel);
        }
        return $question_panel;
    }

    render_question_list(question_list, $(".question-manage .question-list"), question_manage_render_question)
}

//试卷编辑部分列表渲染
function paper_edit_render_question_list()
{
    function paper_edit_render_question(question)
    {
        var $question_panel = get_basic_question_panel(question);
        $question_panel.find(".question-actions").append("" +
            "<i class='fa fa-arrow-up js-question-move-up' title='上移'></i>" +
            "<i class='fa fa-arrow-down js-question-move-down' title='下移'></i>" +
            "<i class='fa fa-pencil js-question-edit' title='修改'></i>" +
            "<i class='fa fa-minus js-question-delete' title='删除'></i>"
        );
        return $question_panel;
    }

    var question_list = app.paper.questionList;
    paper_question_set_index(question_list);
    render_question_list(question_list, $(" .paper-edit  .question-list"), paper_edit_render_question)
}

//试卷浏览部分题目列表渲染
function paper_view_render_question_list()
{
    function paper_view_render_question(question)
    {
        return get_basic_question_panel();
    }

    render_question_list(app.paper.questionList, $(".paper-view .question-list"), paper_view_render_question);
}

//考试部分试卷列表渲染
function paper_exam_render_question_list()
{
    function paper_exam_render_question(question)
    {
        var $question_panel = get_basic_question_panel(question);
        if (QuestionTypeMap(question.type) == "简答题")
        {
            $question_panel.find(".question-workout").append($($("#js-template-question-workout-area").html()));
        }
        return $question_panel;
    }

    render_question_list(app.paper.questionList, $(".paper-exam .question-list"), paper_exam_render_question);
}

//阅卷部分试卷列表渲染
function paper_mark_render_question_list()
{
    render_question_list(app.paper.questionList, $(".paper-mark .question-list"), function (question) {

        var $question_panel = get_basic_question_panel(question);
        $question_panel.find(".question-workout").append($("<pre class='question-workout-area'></pre>"));
        $question_panel.find(".panel-body").append($($("#js-template-question-mark").html()));
        return $question_panel;
    });



}

//显示编辑题目页面
function question_manage_render_question_edit(questionDetail)
{
    var $questionForm = $($("#js-template-question-edit").html());
    app.currentLayer = $questionForm;

    var config = {
        options: function (value, key) {
            return "";
        },
        answer: function (value, key) {
            return "";
        },
        type: function (value, key) {
            return "";
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
        if (fieldContent !== "" && fieldContent !== null)
        {
            set_data_in_field($questionForm.find("[data-field='" + key + "']"), fieldContent);
        }
    }

    var showEdit = true;
    var showSaveAsMine = true;
    var showSaveAsPublic = false;
    //如果是自己的题目，允许编辑
    if (questionDetail.createTeacherId == User.id)
    {
        showEdit = true;
        showSaveAsMine = false;
    } else
    {
        showEdit = false;
        showSaveAsMine = true;
    }
    //如果是管理员，则对公共题目可以编辑
    if (questionDetail.isPrivate == 0 && User.role == '0')
    {
        showEdit = true;
    }
    //如果不是本人创建的题目，不能编辑可见性
    if (questionDetail.createTeacherId != User.id)
    {
        $questionForm.find('[data-field="isPrivate"]').attr("disabled", true);
    }


    showEdit && $questionForm.find(".js-btn-group").append("<input type=\"button\" class=\"btn btn-primary js-edit-submit\" value=\"编辑并提交\">\n")
    showSaveAsMine && $questionForm.find(".js-btn-group").append("<input type=\"button\" class=\"btn btn-default js-save-as-mine\" value=\"保存到我的题库\">\n")


    question_edit_option_status = {
        optionid: 1,
        optionCount: 1
    }
    question_option_parse_json(questionDetail);

    for (key in questionDetail.options)
    {
        var iscorrect = to_answer_array(questionDetail.answer).indexOf(key) != -1;

        question_edit_on_add_option(questionDetail.options[key], iscorrect);
    }

    $(".layui-layer-content").addClass("layer-form");

    //增加选项按钮点击
    app.currentLayer.find(".btn-add-option").on("click", function () {
        question_edit_on_add_option();
    });
    //提交按钮事件
    app.currentLayer.find(".js-edit-submit").on("click", function () {
        question_manage_edit_on_submit('/manage/bank/question/edit.do');
    });
    app.currentLayer.find(".js-save-as-mine").on("click", function () {
        question_manage_edit_on_submit('/manage/bank/question/saveAsMine.do');
    });
//根据选项自动设置题目类型
    question_edit_option_group_on_change();
    return $questionForm;
}


//显示题目列表
function render_question_option_list(question)
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
        var $question_option_label = $question_option.find("label");
        var $question_option_input = $question_option.find("input");

        var option_id = "question_" + question.index + "_" + key;
        $question_option_label.text(question.options[key]);
        $question_option_label.attr("for", option_id);
        $question_option_input.attr("name", "question_" + question.index);
        $question_option_input.attr("data-option-index", key);
        $question_option_input.attr("id", option_id);
        if (question.answer != undefined)
        {
            if (to_answer_array(question.answer).indexOf(key) != -1)
            {
                $question_option.addClass("question-option-correct");
            }
        }
        $question_option_list.append($question_option);
    }
    return $question_option_list;

}

//显示题目详情页面
function question_manage_render_view(questionDetail)
{
    var $questionForm = $($("#js-template-question-view").html());
    var config = {
        options: function (value, key) {
            if (questionDetail.options != null && questionDetail.options != "")
            {
                questionDetail.options = JSON.parse(questionDetail.options);
                return render_question_option_list(questionDetail).prop("outerHTML");
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

var question_edit_option_status;


//试卷中的题目编辑页面
function paper_edit_render_question_edit(question)
{
    var $questionForm = $($("#js-template-paper-question-edit").html());
    app.currentLayer = $questionForm;

    set_data_in_field($questionForm.find("[data-field='code']"), question.code);
    set_data_in_field($questionForm.find("[data-field='outline']"), question.outline);
    set_data_in_field($questionForm.find("[data-field='value']"), question.value);
    set_data_in_field($questionForm.find("[data-field='id']"), question.id);

    question_edit_option_status = {
        optionid: 1,
        optionCount: 1
    }
    question_option_parse_json(question);

    for (key in question.options)
    {
        var iscorrect = to_answer_array(question.answer).indexOf(key) != -1;
        question_edit_on_add_option(question.options[key], iscorrect);
    }

    $(".layui-layer-content").addClass("layer-form");

    //增加选项按钮点击
    app.currentLayer.find(".btn-add-option").on("click", function () {
        question_edit_on_add_option();
    });
    //提交按钮事件
    app.currentLayer.find(".js-submit").on("click", function () {
        paper_edit_question_edit_on_submit(question.index);

    });
    app.currentLayer.find(".js-submit1").on("click", function () {
        console.log(question);
    });
    question_edit_option_group_on_change();
    return $questionForm;
}

//添加一个选项并检查是否超过限制
function question_edit_on_add_option(content, iscorrect)
{
    if (question_edit_option_status.optionCount > 9)
    {
        layer.msg("最多能添加9个选项");
        return;
    }
    question_edit_add_option(question_edit_option_status.optionid, content, iscorrect);
    question_edit_option_status.optionid++;
    question_edit_option_status.optionCount++;
}

//添加一个选项
function question_edit_add_option(optionid, content, iscorrect)
{
    var $option = $($("#js-tempalte-question-edit-option").html());
    $option.attr("id", "option-" + optionid);
    app.currentLayer.find(".option-wrapper").append($option.prop("outerHTML"));
    if (iscorrect == true)
    {
        question_edit_option_toggle_correct(optionid, true);
    }
    //点击正确按钮事件
    app.currentLayer.find("#option-" + optionid + " input").val(content);
    app.currentLayer.find("#option-" + optionid + " .js-valid").on("click", function () {
        question_edit_option_toggle_correct(optionid);
    });
    //点击删除按钮事件
    app.currentLayer.find("#option-" + optionid + " .js-remove").on("click", function () {
        $("#option-" + optionid).remove();
        question_edit_option_status.optionCount--;
        question_edit_option_group_on_change();
    })

    question_edit_option_group_on_change();
}

//更改选项正确性
function question_edit_option_toggle_correct(optionid, iscorrect)
{
    app.currentLayer.find("#option-" + optionid).toggleClass("option-correct", iscorrect);
    app.currentLayer.find("#option-" + optionid + " .js-valid").toggleClass("btn-success", iscorrect).find(".fa").toggleClass("fa-check", iscorrect);
    question_edit_option_group_on_change();
}

//更改题目类型
function question_edit_select_question_type(typeid)
{
    app.currentLayer.find("#select-question-type").val(typeid);
}


//在选项数量和正确答案数量变化时触发，自动判断题目类型
function question_edit_option_group_on_change()
{
    var optionCount = app.currentLayer.find(".form-group-option").length;
    var correctOptionCount = app.currentLayer.find(".option-correct").length;
    if (optionCount == 0)
    {
        question_edit_select_question_type(4);
    }
    else if (correctOptionCount == 1 || correctOptionCount == 0)
    {
        question_edit_select_question_type(2);
    }
    else
    {
        question_edit_select_question_type(3);
    }
}

function question_manage_edit_on_submit(url)
{
    var question = question_manage_edit_collect_data();
    if ($.trim(question.outline) == "")
    {
        layer.alert("题干不能为空");
        return;
    }
    if ($.trim(question['topic.id']) == "")
    {
        layer.alert("题干不能为空");
        return;
    }
    if ($.trim(question['isPrivate']) == undefined)
    {
        layer.alert("可见性不能为空");
        return;
    }
    if ($.trim(question['id']) == "" || question['id'] == undefined || question['id'] == null)
    {
        layer.alert("id不能为空");
        return;
    }

    switch (question['questionType.id'])
    {
        case '2':
            if (question._options.length <= 1)
            {
                layer.alert("单选题至少有两个选项");
                return;
            }
            if (question.answer.length != 1)
            {
                layer.alert("答案必须为一个选项");
                return;
            }
            for (key in question._options)
            {
                if (question._options[key] == "")
                {
                    layer.alert("选项不能为空");
                    return;
                }
            }
            break;
        case '3':
            if (question._options.length <= 1)
            {
                layer.alert("多选题至少有两个选项");
                return;
            }
            if (question.answer.length <= 1)
            {
                layer.alert("多选题至少有两个正确答案");
                return;
            }
            for (key in question._options)
            {
                if (question._options[key] == "")
                {
                    layer.alert("选项不能为空");
                    return;
                }
            }
            break;
        case '4':
            if (question.options != null && question.options.length > 0)
            {
                layer.alert("简答题不能有选项");
                return;
            }
            break;
    }


    $.ajax(
        {
            type: 'post',
            url: url,
            data: question,
            success: function (res) {
                OnResult(res, function (res) {
                        layer.close(app.currentLayerIndex);
                        layer.msg(res.message);
                        question_manage_refresh_question_list();
                    },
                    function () {
                        layer.alert(res.message);
                    }
                )
            }
        }
    )

}


function paper_edit_question_edit_on_submit(index)
{
    var question = paper_question_edit_collect_data();
    question.index = index;

    if ($.trim(question.outline) == "")
    {
        layer.alert("题干不能为空");
        return;
    }
    if ($.trim(question['id']) == "" || question['id'] == undefined || question['id'] == null)
    {
        layer.alert("id不能为空");
        return;
    }

    switch (question.type)
    {
        case '2':
            if (question.options.length <= 1)
            {
                layer.alert("单选题至少有两个选项");
                return;
            }
            if (question.answer.split(',').length != 1)
            {
                layer.alert("答案必须为一个选项");
                return;
            }
            for (key in question.options)
            {
                if (question.options[key] == "")
                {
                    layer.alert("选项不能为空");
                    return;
                }
            }
            break;
        case '3':
            if (question.options.length <= 1)
            {
                layer.alert("多选题至少有两个选项");
                return;
            }
            if (question.answer.split(',').length <= 1)
            {
                layer.alert("多选题至少有两个正确答案");
                return;
            }
            for (key in question.options)
            {
                if (question.options[key] == "")
                {
                    layer.alert("选项不能为空");
                    return;
                }
            }
            break;
        case '4':
            if (question.options != null && question.options.length > 0)
            {
                layer.alert("简答题不能有选项");
                return;
            }
            break;
    }
    app.paper.questionList[index] = question;
    layer.close(app.currentLayerIndex);
    paper_edit_render_question_list();

}

function question_manage_edit_collect_data()
{
    var index = 0;
    var answer = [];
    var options = [];

    app.currentLayer.find(".form-group-option").map(function () {
        var content = $(this).find("input").val();
        if ($(this).hasClass("option-correct"))
        {
            answer.push(index);
        }
        options.push(content);
        index++;
    })
    var data = {
        'id': get_data_in_field(app.currentLayer.find("[data-field='id']")),
        'outline': get_data_in_field(app.currentLayer.find("[data-field='outline']")),
        'code': get_data_in_field(app.currentLayer.find("[data-field='code']")),
        'questionType.id': get_data_in_field(app.currentLayer.find("[data-field='type']")),
        'topic.id': get_data_in_field(app.currentLayer.find("[data-field='topicid']")),
        '_options': options,
        'answer': answer.join(","),
        'isPrivate': get_data_in_field(app.currentLayer.find("[data-field='isPrivate']")),
    };

    return data;

}

function paper_question_edit_collect_data()
{
    var index = 0;
    var answer = [];
    var options = [];
    app.currentLayer.find(".form-group-option").map(function () {
        var content = $(this).find("input").val();
        if ($(this).hasClass("option-correct"))
        {
            answer.push(index);
        }
        options.push(content);
        index++;
    })
    var data = {
        'id': get_data_in_field(app.currentLayer.find("[data-field='id']")),
        'outline': get_data_in_field(app.currentLayer.find("[data-field='outline']")),
        'code': get_data_in_field(app.currentLayer.find("[data-field='code']")),
        'type': get_data_in_field(app.currentLayer.find("[data-field='type']")),
        'value': get_data_in_field(app.currentLayer.find("[data-field='value']")),
        'options': options,
        'answer': answer.join(","),
    };

    for (key in data)
    {
        if (data[key] == null || data[key] == "" || data[key] == undefined || data[key] == [])
        {
            delete data[key];
        }
    }

    return data;
}


function paper_edit_render_paper_title_edit()
{
    var $editForm = $($("#js-template-edit-title").html());
    app.currentLayer = $editForm;
    $editForm.find("[name='title']").val(app.paper.title);
    $editForm.find("[name='comment']").val(app.paper.comment);
    $editForm.find(".js-paper-edit-title-submit").on("click", function () {
            var data = {
                title: $editForm.find("[name='title']").val(),
                comment: $editForm.find("[name='comment']").val()
            }
            app.paper.title = data.title;
            app.paper.comment = data.comment;
            render_paper_title();
            layer.close(app.currentLayerIndex);
            layer.msg("编辑成功");
        }
    );

    return $editForm;
}

function paper_edit_on_edit_title()
{
    app.currentLayerIndex = layer.open(
        {
            type: 5,
            shadeClose: true, //点击遮罩关闭
            closeBtn: 2,
            title: '标题编辑',
            area: ['700px', '280px'],
            content: "",
            success: function (a) {
                a.find('.layui-layer-content').append(paper_edit_render_paper_title_edit());
            },
            end: function () {

            }
        }
    )
}

function paper_edit_on_submit()
{
    $.ajax(
        {
            url: '/paper/edit.do',
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(app.paper),
            success: function (res) {
                OnResult(res, function (res) {
                    layer.msg("提交成功")
                })
            }
        }
    )
}


function render_paper_title()
{
    $(".title-area .title").text(app.paper.title);
    $(".title-area .comment").text(app.paper.comment);
}


//------------workout-and-mark--------------------//

//用index为数组下标重组questionList
function reArrangeQuestionList(list)
{
    var questionlist = [];
    list.map(function (item) {
            questionlist[item.index] = item;
            item.needUpdate = false;
        }
    )
    return questionlist;
}

function navigate_to_question(index)
{
    var $panel = $(".panel-question[data-index='" + index + "']");
    $(".question-list .focus").removeClass("focus");
    $panel[0].scrollIntoView(true);
    var hread = $(window).scrollTop() - 150;
    $(window).scrollTop(hread);

    $panel.addClass("focus");
    setTimeout(function () {
        $panel.removeClass("focus");
    }, 2500);
}
