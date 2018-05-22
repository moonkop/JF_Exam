
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="display: flex;">
    <div class="paper-edit">
        <div class="title-area">
            <div class="title"></div>
            <div class="comment"></div>
        </div>

        <div class="paper-question-list">

        </div>
    </div>
</div>
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
            id:'${paper.id}',
            title:'${paper.title}',
            comment:'${paper.comment}',
            questionList:${questionList},
        }
    };
</script>