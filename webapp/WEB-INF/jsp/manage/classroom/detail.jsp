<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>


<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            班级管理 - 详细信息
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属学校</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${classroom.schoolVo.name}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">班级</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static">${classroom.name}</p>
                                </div>
                            </div>
                        
                            <div class="col-sm-offset-2">
                                <a class="btn btn-default" href="/manage/classroom/edit?id=${classroom.id}">编辑</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- end content -->
