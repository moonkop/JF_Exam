<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- start content -->

    <div class="app-header">
        <h3 style="display: inline-block;">题目管理</h3>
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
    </div>
    <div class="question-manage">

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
                                'worker': false,
                                "check_callback": true,
                                "themes": {"icons": false}
                            },
                            "plugins": [
                                "contextmenu",
                            ],

                            'contextmenu': {
                                'items': function (node) {
                                    var tmp = {
                                        create: {
                                            label: "添加题目",
                                            action: function (data) {
                                                var node = getJstree().get_node(data.reference);
                                                layer.open({
                                                    type: 5,
                                                    shadeClose: true, //点击遮罩关闭
                                                    closeBtn: 2,
                                                    title: '请输入题目名称',
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
                                                                            getResourceTree();
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
                getResourceTree();
                $("#select-subject").on("change", function () {
                    getResourceTree();

                })
            })


        </script>
        <div id="jstree">
        </div>

    </div>

<script id="js-add-form-template" type="text/template">
    <div class="layer-popup">
        <form role="form" class="form-horizontal" id="form-add-topic">
            <input type="hidden" name="parent">
            <div class="form-group">
                <label class="col-sm-4 control-label">题干</label>
                <div class="col-sm-8">
                    <textarea type="text" class="form-control" id="inputID" name="name"/>
                </div>
            </div>
            <button type="button" class="js-layer-form-add-btn btn btn-primary  pull-right">
                <i class="glyphicon glyphicon-plus"></i>添加
            </button>
        </form>
    </div>
</script>
<!-- end content -->
