<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->
        <div class="row">
            <div class="col-lg-12">
                <h3>
                    角色管理 - 详细信息
                </h3>
                <div class="panel panel-primary main-panel">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属学校</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${role.name}</p>
                                        </div>
                                    </div>
                                    <div id="jstree">

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
                                                    url: '/manage/roleResource/tree.do?edit=0&id=${role.id}',
                                                    success: function (res) {
                                                        $('#jstree').jstree({
                                                            'core': {
                                                                'data': res
                                                            }
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
                                        <a class="btn btn-default" href="/manage/role/edit?id=${role.id}">编辑</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- end content -->
