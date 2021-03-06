<%--
  Created by IntelliJ IDEA.
  User: moonkop
  Date: 2018/5/19
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="display: none">


    <script id="js-template-question-panel" type="text/html">
        <div class="panel panel-question">
            <div class="panel-body">
                <div class="question-header">
                <span class="question-index">
                    </span>
                    <span class="question-type">
                    </span>
                    <span class="question-value">
                    </span>
                    <span class="question-answer">
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
                <div class="question-workout">

                </div>
            </div>
        </div>
    </script>

    <script id="js-template-question-mark" type="text/html">
        <div class="question-mark">
            <div class="question-mark-info">
                <span class="question-mark-info-teacher"></span>
                <span class="question-mark-info-time"></span>
            </div>
            <div class="question-mark-area">
                <%--<div class="question-mark-remark">--%>
                    <%--<textarea class="question-remark-area"></textarea>--%>
                <%--</div>--%>
                <div class="question-mark-range">
                    <input type="text" class="js-question-mark-range">
                </div>
                <div class="question-mark-score">
                    <input type="text" class="question-mark-score-input">
                    <span>分</span>
                </div>
            </div>
        </div>
    </script>

    <script id="js-template-question-result" type="text/html">

        <div class="question-result">

            <span class="question-result-score"></span>
            <span class="question-result-workout"></span>
            <span class="question-result-teacher"></span>
            <div class="question-result-remark">

            </div>
        </div>

    </script>

    <script id="js-template-question-report" type="text/html">
        <div class="question-report">
            <span class="question-report-scoreAvg"></span>
            <span class="question-report-scoreRate"></span>
            <span class="question-report-scoreMax"></span>
            <span class="question-report-scoreMin"></span>
        </div>
    </script>


    <script id="js-template-question-workout-area" type="text/html">
        <textarea class="form-control" style="width: 100%" cols="5"></textarea>
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
            <div style="display: none;">
                <input type="text" data-field="id">
                <input type="text" data-field="topicid">
            </div>
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
            <div class="option-wrapper">

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
                    <select name="type" id="select-question-type" data-field="type" class="form-control">
                        <option value="2">单选题</option>
                        <option value="3">多选题</option>
                        <option value="4">简答题</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">可见性</label>
                <div class="col-sm-9">
                    <select name="type" id="select-question-private" data-field="isPrivate" class="form-control">
                        <option value="1">仅自己可见</option>
                        <option value="0">所有人可见</option>
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
            <div class="col-sm-offset-2 js-btn-group">

            </div>
        </div>
    </script>
    <script id="js-template-paper-question-edit" type="text/html">

        <div class="form-horizontal">
            <div style="display: none;">
                <input type="text" data-field="id">
            </div>
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
            <div class="option-wrapper">

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
                    <select name="type" id="select-question-type" data-field="type" class="form-control">
                        <option value="2">单选题</option>
                        <option value="3">多选题</option>
                        <option value="4" selected>简答题</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">分值</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" data-field="value">
                </div>
            </div>
            <div class="col-sm-offset-2 js-btn-group">
                <div class="btn btn-primary js-submit">
                    确认修改
                </div>
                <%--<div class="btn btn-primary js-submit1">--%>
                    <%--确认修改1--%>
                <%--</div>--%>
            </div>
        </div>
    </script>


    <script id="js-tempalte-question-edit-option" type="text/html">
        <div class="form-group form-group-option">
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



    <script id="js-template-edit-title" type="text/html">

        <div class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-2 control-label">标题</label>
                <div class="col-sm-9">
                    <input class="form-control" type="text" name="title">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="comment" rows="5"></textarea>
                </div>
            </div>


            <div class="col-sm-offset-2 js-btn-group">
                <button class="btn btn-primary js-paper-edit-title-submit">
                    提交
                </button>
            </div>
        </div>
    </script>

</div>