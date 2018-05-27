<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

        <!-- start content -->

        <div class="row">
            <div class="col-lg-12">

                    <h3 style="display: inline-block;">知识点管理</h3>
                    <div class="" style="display: inline-block;margin-left: 30px;">

                            <label>科目</label>
                            <select id="select-subject" class="input-sm">
                                <c:forEach items="${requestScope.subjects}" var="subject">
                                    <option value="${subject.id}"
                                            <c:if test="${subjectSelected==subject.id}">selected</c:if>   >
                                            ${subject.name}
                                    </option>
                                </c:forEach>
                            </select>
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
                                .on('move_node.jstree', function (e, data) {
                                    $.ajax(
                                        {
                                            url: '/manage/bank/topic/move.do',
                                            data: {'id': data.node.id, 'parent': data.parent},
                                            success: function (res) {
                                                OnResult(res);
                                                getTopicTree();
                                                // data.instance.refresh();
                                            }
                                        }
                                    )
                                })
                                .on('rename_node.jstree', function (e, data) {
                                    $.ajax(
                                        {
                                            url: '/manage/bank/topic/rename.do',
                                            data: {'id': data.node.id, 'text': data.text},
                                            success: function (res) {
                                                OnResult(res);
                                                getTopicTree();
//                                                data.instance.set_id(data.node, d.id);
                                                //data.instance.refresh();
                                            }
                                        }
                                    )
                                }).on('create_node.jstree', function (e, data) {
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
                                                    label: "添加知识点",
                                                    action: function (data) {
                                                        var node = getJstree().get_node(data.reference);
                                                        layer.open({
                                                            type: 5,
                                                            shadeClose: true, //点击遮罩关闭
                                                            closeBtn: 2,
                                                            title: '请输入知识点名称',
                                                            area: ['320px', '170px'],
                                                            content: $("#js-add-form-template").html(),
                                                            success: function () {
                                                                $("#form-add-topic>input[name='parent']").val(node.id);
                                                                $(".js-layer-form-add-btn").on("click", function () {
                                                                        $.ajax(
                                                                            {
                                                                                url: "/manage/bank/topic/create.do",
                                                                                data: getFormData("form-add-topic"),
                                                                                success: function (res) {
                                                                                    OnResult(res);
                                                                                    getTopicTree();
                                                                                }
                                                                            }
                                                                        )
                                                                    }
                                                                )

                                                            },
                                                            end: function () {
                                                            }
                                                        });
                                                    }
                                                },
                                                delete: {
                                                    label: "删除知识点",
                                                    action: function (data) {
                                                        var id = getJstree().get_node(data.reference).id;
                                                        if (confirm("真的要删除吗？"))
                                                        {
                                                            $.ajax(
                                                                {
                                                                    url: "/manage/bank/topic/delete.do?id=" + id,
                                                                    success: function (res) {
                                                                        OnResult(res, function () {
                                                                            alert(res.message);
                                                                            getTopicTree();
                                                                        });
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    }
                                                },
                                                edit: {
                                                    label: "编辑知识点",
                                                    action: function (data) {
                                                        obj = getJstree().get_node(data.reference);
                                                        getJstree().edit(obj);
                                                    }
                                                }
                                            };
                                            return tmp;
                                        }
                                    }
                                });

                        }


                        function getTopicTree()
                        {
                            $.ajax({
                                url: '/manage/bank/topic/treeBySubject.do?subjectID=' + $("#select-subject").val(),
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
                        getTopicTree();
                        $("#select-subject").on("change", function () {
                            getTopicTree();

                        })
                    })


                </script>

                <div id="jstree">
                </div>


            </div>

        </div>

<script id="js-add-form-template" type="text/template">
    <div class="layer-popup">
        <form role="form" class="form-horizontal" id="form-add-topic">
            <input type="hidden" name="parent">
            <div class="form-group">
                <label class="col-sm-4 control-label">知识点名称</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="inputID" name="name">
                </div>
            </div>
            <button type="button" class="js-layer-form-add-btn btn btn-primary  pull-right">
                <i class="glyphicon glyphicon-plus"></i>添加
            </button>
        </form>
    </div>
</script>

        <!-- end content -->
