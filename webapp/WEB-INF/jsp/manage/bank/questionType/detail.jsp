<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    题目类型管理 - 详细信息
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">题目类型id</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${questionType.id}</p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">题目类型名称</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${questionType.name}</p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">题目默认分数</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${questionType.score}</p>
                                        </div>
                                    </div>

                                    <div class="col-sm-offset-2">
                                        <a class="btn btn-default" href="/manage/bank/questionType/edit?id=${questionType.id}">编辑</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- end content -->
