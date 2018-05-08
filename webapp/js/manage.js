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

getFormData=function(formid)
{
    data={};
    $.each($("#"+formid).serializeArray(), function (index, item) {
        data[item.name] = item.value;
    })
    return data;

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
            onsuccess();
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
            onfailure();

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
        layer.msg('result.message');
    }
}

function defaultOnFailure(res)
{

    layer.alert("操作失败" + res.code + ":" + res.message);
}
