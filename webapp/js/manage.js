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
        alert("操作成功");
    } else if (result.message === undefined)
    {
        alert("操作结果未知");
    } else
    {
        alert(result.message);
    }
}

function defaultOnFailure(res)
{
    alert("操作失败" + res.code + ":" + res.message);
}
