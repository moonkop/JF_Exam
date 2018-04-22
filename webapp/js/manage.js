queryParams = function (params) {
    var temp = {
        pageNum: params.offset / params.limit,
        pageSize: params.limit
    };
    return temp;
}