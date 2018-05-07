<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<head>
    <title>学生管理</title>
    <%@include file="/WEB-INF/components/header.jsp" %>
    <script src="/vendor/jstree/jstree.js"></script>
    <link rel="stylesheet" href="/vendor/jstree/themes/default/style.css"/>
</head>

<body>

<div id="wrapper">
    <%@include file="/WEB-INF/components/navbar.jsp" %>
    <%@include file="/WEB-INF/components/sidebar.jsp" %>

    <div id="page-wrapper">
        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">
                <h3>
                    资源管理
                </h3>
                <div class="table-btns">
                    <a class="btn btn-primary" href="/manage/resource/edit"> 添加资源</a>
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

                        function initTree()
                        {
                            $('#jstree')
                                .on("refresh.jstree", function (event, data) {
                                    getJstree().open_all();
                                })
                                .jstree({
                                    'core': {
                                        'worker': false
                                    },
                                    "plugins": [
                                        "contextmenu"
                                    ],
                                    'contextmenu': {
                                        'items': function (node) {
                                            var tmp = {
                                                create: {
                                                    label: "添加资源",
                                                    action: function (data) {
                                                        var id = getJstree().get_node(data.reference).id;
                                                        window.location.href = "/manage/resource/edit?parent.id=" + id;
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
    </div>
</div>
<%@include file="/WEB-INF/components/footer.jsp" %>
</body>

</html>
