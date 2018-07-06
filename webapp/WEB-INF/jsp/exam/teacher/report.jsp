<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script src="/dist/js/question-manage.js"></script>
<%@include file="/WEB-INF/components/question-manage-templates.jsp" %>
<script>
    var app = {
        name: 'exam-preview',
        exam: {
            id: '${exam.id}',

        },
        paper: {
            questionList:${questionList},
        },
        report:${report_json}
    };
    $(document).ready(function () {
        app.paper.questionList = sort_question_List(app.paper.questionList);

        var report_question_list = [];
        app.report.questionReportList.map(function (item) {
            report_question_list[item.index] = item;

        });

        var questionList = [];

        app.paper.questionList.map(function (item) {
                questionList[item.index] = item;
                questionList[item.index].report = report_question_list[item.index];
            }
        );

        app.paper.questionList


        render_paper_title();
        exam_report_render_question_list();

    });


</script>
<div class="row">

    <div class="col-lg-12">
        <h3>
            考试结果
        </h3>

        <div class="panel panel-default" >
            <div class="panel-heading">
                基本信息
            </div>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">考试名称</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${exam.name}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">满分</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${paper.fullMark}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">平均分</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.scoreAvg}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">最高分</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.scoreMax}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">最低分</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.scoreMin}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">考试人数</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.studentCount}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">参与人数</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.attendCount}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">缺席人数</label>
                    <div class="col-sm-8">
                        <p class="form-control-static">${report.studentCount - report.attendCount}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                试卷详情
            </div>

            <div class="panel-body">

                <div class="paper-exam-report">
                    <div class="title-area">
                        <div class="title"></div>
                        <div class="comment"></div>
                    </div>
                    <div class="question-list">
                        <div class="panel panel-question" id="paper_question_0" data-index="0" data-type="3"
                             data-id="6e4cb61ba6cf4d659273423d2c175253">
                            <div class="panel-body">
                                <div class="question-header">
                                    <span class="question-index">1. </span>
                                    <span class="question-type">多选题</span>
                                    <span class="question-value">5分</span>
                                    <span class="question-answer">答案：BCD</span>
                                    <span class="question-actions"></span>
                                </div>
                                <div class="question-body">
                                    <span class="question-outline">我是第四道题</span>
                                    <div class="question-code">
                                        <pre><code class="hljs"></code></pre>
                                    </div>
                                    <div class="question-option-list">

                                        <%--<div class="panel-group" id="accordion">--%>
                                        <%--<div class="panel panel-default">--%>
                                        <%--<div class="panel-heading">--%>
                                        <%--<div style="position: absolute; background-color: #ffF1ff"></div>--%>
                                        <%--<h4 class="panel-title">--%>
                                        <%--<a data-toggle="collapse" href="#collapseOne">--%>
                                        <%--点击我进行展开，再次点击我进行折叠。第 1 部分--%>
                                        <%--</a>--%>
                                        <%--</h4>--%>
                                        <%--</div>--%>
                                        <%--<div id="collapseOne" class="panel-collapse collapse in">--%>
                                        <%--<div class="panel-body">--%>
                                        <%--Nihil anim keffiyeh helvetica, craft beer labore wes anderson--%>
                                        <%--cred nesciunt sapiente ea proident. Ad vegan excepteur butcher--%>
                                        <%--vice lomo.--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="panel panel-default">--%>
                                        <%--<div class="panel-heading">--%>
                                        <%--<h4 class="panel-title">--%>
                                        <%--<a data-toggle="collapse"--%>
                                        <%--href="#collapseTwo">--%>
                                        <%--点击我进行展开，再次点击我进行折叠。第 2 部分--%>
                                        <%--</a>--%>
                                        <%--</h4>--%>
                                        <%--</div>--%>
                                        <%--<div id="collapseTwo" class="panel-collapse collapse">--%>
                                        <%--<div class="panel-body">--%>
                                        <%--Nihil anim keffiyeh helvetica, craft beer labore wes anderson--%>
                                        <%--cred nesciunt sapiente ea proident. Ad vegan excepteur butcher--%>
                                        <%--vice lomo.--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="panel panel-default">--%>
                                        <%--<div class="panel-heading">--%>
                                        <%--<h4 class="panel-title">--%>
                                        <%--<a data-toggle="collapse"--%>
                                        <%--href="#collapseThree">--%>
                                        <%--点击我进行展开，再次点击我进行折叠。第 3 部分--%>
                                        <%--</a>--%>
                                        <%--</h4>--%>
                                        <%--</div>--%>
                                        <%--<div id="collapseThree" class="panel-collapse collapse">--%>
                                        <%--<div class="panel-body">--%>
                                        <%--Nihil anim keffiyeh helvetica, craft beer labore wes anderson--%>
                                        <%--cred nesciunt sapiente ea proident. Ad vegan excepteur butcher--%>
                                        <%--vice lomo.--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>


                                        <div class="question-option question-option-checkbox">
                                            <input type="checkbox" name="question_0" data-option-index="0"
                                                   id="question_0_0" disabled="disabled">
                                            <label for="question_0_0">const</label>
                                        </div>
                                        <div class="question-option question-option-checkbox question-option-correct">
                                            <input type="checkbox" name="question_0" data-option-index="1"
                                                   id="question_0_1" disabled="disabled">
                                            <label for="question_0_1">TRUE</label>
                                        </div>
                                        <div class="question-option question-option-checkbox question-option-correct">
                                            <input type="checkbox" name="question_0" data-option-index="2"
                                                   id="question_0_2" disabled="disabled">
                                            <label for="question_0_2">FALSE</label>
                                        </div>
                                        <div class="question-option question-option-checkbox question-option-correct">
                                            <input type="checkbox" name="question_0" data-option-index="3"
                                                   id="question_0_3" disabled="disabled">
                                            <label for="question_0_3">this</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="question-workout">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>