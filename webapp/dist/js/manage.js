tableResponseHandler = function (res) {
    return res.payload;
}

queryParams = function (params) {
    var temp = {
        pageNum: params.pageNumber,
        pageSize: params.limit,
        offset:params.offset
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

get_data_in_field = function ($element) {
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

set_data_in_field = function ($element, data) {

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

function myajax(config)
{
    if (config.success != undefined && config.success != null)
    {
        var success = config.success;
        config.success = function (res) {
            //success 请求成功，并且服务器返回200，error 请求成功，服务器返回错误码code=404
            OnResult(res, success, config.error);
        }
    }
    if (config.error == undefined)

    //如果直接请求失败，则为网络错误，重写error方法
    {
        config.error = function () {
            layer.msg("网络错误");
        }
    }
    $.ajax(config);
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
            layer.alert(res.message, function () {
                window.location.href = "/";
            });
            break;
        default:
            var message = "操作失败" + res.code + ":" + res.message;
            for (key in res.payload)
            {
                message += "<br>" + key + "  " + res.payload[key];
            }
            layer.alert(message);

            break;

    }

}


function DateTimeStringToTimeStamp(string)
{
    try
    {
        return new Date(string).getTime();
    } catch (err)
    {
        console.error(err);
        return null;
    }
}

function TimeStampTDateTimeString(timestamp)
{
    try
    {
        return new Date(timestamp).Format("yyyy-MM-dd hh:mm:ss");
    } catch (err)
    {
        console.error(err);
        return null;
    }
}

function emptyToUndefined(obj)
{
    return obj;
}


layer.config({skin: 'layui-layer-exam'});
$(document).ready(function () {
    $(".js-back").on("click", function () {
        window.history.go(-1);
    })
})

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
    {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o)
    {
        if (new RegExp("(" + k + ")").test(fmt))
        {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}
function convertTimeToStr(time)
{

    var floor = [3600 * 24 * 365, 3600 * 24, 3600, 60, 1];
    var str = "";
    var started = false;
    floor.forEach(function (item) {
            var     num=parseInt(time/item) ;
            if(started)
            {
                str+=":";
            }
            time%=item;
            if(num!=0||started){
                str+=num;
                started=true;

            }
        }
    )
    return str;
}