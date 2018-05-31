<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">
                <h3>
                    资源管理
                </h3>

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

                        function initTree()
                        {
                            $('#jstree')
                                .on("refresh.jstree", function (event, data) {
                                    getJstree().open_all();
                                })
                                .on('move_node.jstree', function (e, data) {
                                    $.ajax(
                                        {
                                            url: '/manage/resource/move.do',
                                            data: {'id': data.node.id, 'parent': data.parent},
                                            success: function (res) {
                                                OnResult(res);
                                                getResourceTree();
                                            }
                                        }
                                    )
                                })

                                .jstree({
                                    'core': {
                                        'worker': false,
                                        "check_callback": true
                                    },
                                    "plugins": [
                                        "contextmenu",
                                        "dnd"
                                    ],
                                    'contextmenu': {
                                        'items': function (node) {
                                            var tmp = {
                                                create: {
                                                    label: "添加资源",
                                                    action: function (data) {
                                                        var id = getJstree().get_node(data.reference).id;
                                                        window.location.href = "/manage/resource/edit?parent=" + id;
                                                    }
                                                },
                                                delete: {
                                                    label: "删除资源",
                                                    action: function (data) {
                                                        var id = getJstree().get_node(data.reference).id;
                                                        if (confirm("真的要删除吗？"))
                                                        {
                                                            $.ajax(
                                                                {
                                                                    url: "/manage/resource/delete.do?id=" + id,
                                                                    success: function (res) {
                                                                        OnResult(res, function () {
                                                                            alert(res.message);
                                                                            getResourceTree();
                                                                        });
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    }
                                                },
                                                edit: {
                                                    label: "编辑资源",
                                                    action: function (data) {
                                                        var id = getJstree().get_node(data.reference).id;
                                                        window.location.href = "/manage/resource/edit?id=" + id;
                                                    }
                                                }
                                            };
                                            return tmp;
                                        }
                                    }
                                });

                        }


                        function getResourceTree()
                        {
                            $.ajax({
                                url: '/manage/resource/tree.do',
                                success: function (res) {
                                    OnResult(res, function () {
                                            getJstree().settings.core.data = res.payload.rows;
                                            getJstree().refresh();
                                        }
                                    )
                                }
                            })
                        }

                        initTree();
                        getResourceTree();

                    })


                </script>
                <div id="jstree">

                </div>


            </div>

        </div>

        <!-- end content -->
