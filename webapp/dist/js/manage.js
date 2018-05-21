tableResponseHandler = function (res) {
    return res.payload;
}

queryParams = function (params) {
    var temp = {
        pageNum: params.pageNumber,
        pageSize: params.limit
    };
    return temp;
}

getFormData = function (formid) {
    data = {};
    $.each($("#" + formid).serializeArray(), function (index, item) {
        data[item.name] = item.value;
    })
    return data;

}

 get_data_in_field=function($element)
{
    if ($element == undefined || $element[0] == undefined)
    {
        return;
    }
    switch ($element[0].tagName)
    {

        case'TEXTAREA':
        case'INPUT':
        case'SELECT':
            return $element.val();

        case 'P':
        case 'PRE':
        case 'CODE':
        case 'DIV':
        default:
            return $element.html();
            break;
    }
}

set_data_in_field= function($element, data)
{

    if ($element == undefined || $element[0] == undefined)
    {
        return;
    }
    switch ($element[0].tagName)
    {
        case 'PRE':
        case 'CODE':
        case 'DIV':
        case 'P':
            return $element.html(data);
            break;
        case'TEXTAREA':
        case'INPUT':
        case'SELECT':
            return $element.val(data);

    }
}



function OnResult(result, onsuccess, onfailure)
{
    if (result.code == 100)
    {
        if (onsuccess === undefined)
        {
            defaultOnSuccess(result);

        } else if (onsuccess === 0)
        {

        } else
        {
            onsuccess(result);
        }
    } else
    {
        if (onfailure === undefined)
        {
            defaultOnFailure(result);
        } else if (onfailure === 0)
        {

        } else
        {
            onfailure(result);
        }
    }
}

function defaultOnSuccess(result)
{
    if (result.message == "ok")
    {
        layer.msg('操作成功');
    } else if (result.message === undefined)
    {
        layer.msg('操作结果未知');
    } else
    {
        layer.msg(result.message);
    }
}

function defaultOnFailure(res)
{
    switch (res.code)
    {
        case 401:
            layer.alert(res.message, {skin: 'layui-layer-molv', closeBtn: 0}, function () {
                window.location.href = "/";
            });
            break;
        default:
            layer.alert("操作失败" + res.code + ":" + res.message);
            break;

    }

}
