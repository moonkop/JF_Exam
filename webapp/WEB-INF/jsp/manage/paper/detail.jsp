
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class="paper-view">
        <div class="title-area">
            <div class="title"></div>
            <div class="comment"></div>
        </div>

        <div class="question-list">

        </div>
    </div>
    <div class="text-right">
        <button class="btn btn-default js-back">
            返回
        </button>
    </div>


<script src="/dist/js/question-manage.js"></script>
<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>

<script>
    var app = {
        name: 'paper-edit',
        paper: {
            id:'${paper.id}',
            title:'${paper.title}',
            comment:'${paper.comment}',
            questionList:${questionList},
        }
    };
    $(document).ready(function()
    {
        paper_view_render_question_list();
        render_paper_title();
    })
</script>