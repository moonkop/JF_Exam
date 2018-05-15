<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    题目类型管理 - 编辑
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form" class="form-horizontal" action="/manage/bank/questionType/edit.do">
                                    <input type="text" class="form-control" style="display: none;" name="id"
                                           value="${questionType.id}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">题目类型id</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${questionType.id}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-name">题目类型名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-name" name="name"
                                                   value="${questionType.name}"
                                                   placeholder="请输入题目类型名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="input-name">题目默认分数</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="input-score" name="score"
                                                   value="${questionType.score}"
                                                   placeholder="请输入默认分数">
                                        </div>
                                    </div>
                                    <div class="col-sm-offset-2">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button type="reset" class="btn btn-default js-cancel">重置</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- end content -->
