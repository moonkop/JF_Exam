<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->
<div class="row">
    <div class="col-lg-12">
        <h3>
            角色管理 - 编辑
        </h3>
        <div class="panel panel-primary main-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form" class="form-horizontal" action="/manage/role/edit.do" id="mainform">
                            <div class="form-group" style="display:none">
                                <input type="text" class="form-control" id="id" name="id" style="display: none"
                                       value="${role.id}">
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-name">角色名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-name" name="name"
                                           value="${role.name}"
                                           placeholder="请输入角色名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-seq">序号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-seq" name="seq"
                                           value="${role.seq}"
                                           placeholder="请输入角色名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="input-remark">备注</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="input-remark" name="remark"
                                           value="${role.remark}"
                                           placeholder="请输入角色名">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色资源</label>
                                <div class="col-sm-8">
                                    <div id="jstree">

                                    </div>
                                </div>
                            </div>


                            <script type="text/javascript">
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
                                    function getResourceTree()
                                    {
                                        $.ajax({
                                            url: '/manage/roleResource/tree.do?edit=1&id=${role.id}',
                                            success: function (res) {
                                                $('#jstree').jstree({
                                                    'core': {
                                                        'data': res
                                                    },
                                                    "plugins": [
                                                        "checkbox"
                                                    ],
                                                    "checkbox": {
                                                        "three_state": false,//父子级别级联选择
                                                    },
                                                });
                                                setTimeout(function () {
                                                    $('#jstree').jstree().open_all();
                                                }, 100);
                                            }
                                        })
                                    }

                                    getResourceTree();

                                })


                            </script>


                            <div class="col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">提交</button>
                                <button class="btn btn-default js-cancel">取消</button>
                            </div>
                        </form>

                        <script>

                            $(document).ready(function () {


                                $("form").on("submit", function () {
                                    var data = getFormData("mainform");
                                    data["resourceIds"] = getJstree().get_checked();


                                    $.ajax(
                                        {
                                            url: "/manage/role/edit.do",
                                            type: "post",
                                            data: data,

                                            success: function (res) {
                                                OnResult(res,
                                                    function (res) {
                                                        setTimeout( 500)
                                                    }
                                                );
                                            }
                                        }
                                    );

                                    $.myajax(
                                        {
                                            url: "/manage/role/edit.do",
                                            type: "post",
                                            data: data,
                                            resSuccess:function () {
                                                setTimeout(function () {
                                                    window.location.href = "/manage/role";
                                                }, 500)
                                            },
                                        }
                                    )




                                    return false;

                                })

                            });

                        </script>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- end content -->
