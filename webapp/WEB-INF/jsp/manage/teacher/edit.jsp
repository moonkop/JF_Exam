<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>教师管理 - 添加</title>
    <%@include file="/WEB-INF/components/header.jsp"%>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp"%>
    <%@include file="/WEB-INF/components/sidebar.jsp"%>

    <div id="page-wrapper">
<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            教师管理 - 添加
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">账号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputID"
                                           value="caiji001">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="inputPassword"
                                           placeholder="如需修改，请输入">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputName"
                                           value="夏岩">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="selectRole" class="col-sm-2 control-label">身份</label>
                                <div class="col-sm-8">
                                    <select name="selectRole" id="selectRole" class="form-control">
                                        <option value="teacher">教师</option>
                                        <option value="admin">管理员</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputIDCard" name="inputIDCard"
                                           value="320502199504057884">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail" name="inputEmail"
                                           value="xiayan@qq.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTel" class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputTel" name="inputTel"
                                           value="18015151615">
                                </div>
                            </div>
                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <button type="submit" class="btn btn-default">取消</button>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- end content -->
    </div>
</div>
<%@include file="/WEB-INF/components/footer.jsp"%>
</body>

</html>
