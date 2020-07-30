$(window).on("load",function(){

    /**
     * @author p1n93r
     * 查询快递模块：请求后台，显示快递信息
     */
    $(function() {
        $("#express-form").validator();
        $("#express-form").on("submit",
            function(a) {
                if (!a.isDefaultPrevented()) {
                    //开始查询前，设置一个等待提示框
                    var prePostTips = '<div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>正在查询，请等待...</div>';
                    $("#express-form").find(".messages").html(prePostTips);
                    //发送数据，获取查询结果
                    //待发送的数据
                    var postData=$(this).serialize();
                    $.ajax({
                        type: "GET",
                        url: "user/queryExpress",
                        data: postData,
                        success: function(d) {
                            $("#express-timeline").html("");
                            $("#express-msg").html("");
                            var status=d.status;
                            //设置一个消息回调
                            var e = "alert-" + d.alert;
                            var f = d.message;
                            var c = '<div class="alert ' + e + ' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' + f + "</div>";
                            if (e && f) {
                                $("#express-form").find(".messages").html(c);
                                $("#express-form")[0].reset()
                            }
                            //处理json数据，加载时间轴(只有正确查询才能设置显示是时间轴)
                            if(status=="0"){
                                //首先显示基本的信息
                                var result=d.result;
                                var isSign="否";
                                if(result.issign=="1"){
                                    isSign="是";
                                }
                                basicStr="        <div class=\"row \">\n" +
                                    "            <div class=\"col-10 offset-1\">\n" +
                                    "                <div class=\"row\">\n" +
                                    "                    <div class=\"col-md-4\">\n" +
                                    "                        <p class=\"mb-2\">快递员姓名: <span class=\"text-dark\">"+result.courier+"</span></p>\n" +
                                    "                        <p class=\"mb-0\">快递员电话: <span class=\"text-dark\">"+result.courierPhone+"</span></p>\n" +
                                    "                    </div>\n" +
                                    "                    <div class=\"col-md-4\">\n" +
                                    "                        <p class=\"mb-2\">快递公司: <span class=\"text-dark\">"+result.expName+"</span></p>\n" +
                                    "                        <p class=\"mb-0\">单号: <span class=\"text-dark\">"+result.number+"</span></p>\n" +
                                    "                    </div>\n" +
                                    "                    <div class=\"col-md-4\">\n" +
                                    "                        <p class=\"mb-2\">是否签收: <span class=\"text-dark\">"+isSign+"</span></p>\n" +
                                    "                        <p class=\"mb-0\">已耗时长: <span class=\"text-dark\">"+result.takeTime+"</span></p>\n" +
                                    "                    </div>\n" +
                                    "                </div>\n" +
                                    "            </div>\n" +
                                    "        </div>";
                                $("#express-msg").append(basicStr);
                                var nodes = result.list;
                                $.each(nodes,function(n,value) {
                                    //express-timeline
                                    entry="        <div class=\"entry\">\n" +
                                        "            <div class=\"title\">\n" +
                                        "                <span>"+value.time+"</span>\n" +
                                        "            </div>\n" +
                                        "            <div class=\"body\">\n" +
                                        "                <p>"+value.status+"</p>\n" +
                                        "            </div>\n" +
                                        "        </div>";
                                    $("#express-timeline").append(entry);
                                });
                                //添加时间轴
                                $("#express-timeline").append("<span class=\"timeline-line\"></span>");
                                //请求添加历史搜索记录
                                var data={};
                                data.num=postData.split("=")[1];
                                $.post('user/addUserExpress',data);
                            }
                        }
                    });
                    return false
                }
            })
    });

    /**
     * @author p1n93r
     * 查询快递模块：自动补全历史搜索记录
     */
    typeof $.typeahead === 'function' && $.typeahead({
        input: "#expressNo",
        dynamic: true,
        minLength: 1,
        maxItem: 15,
        order: "asc",
        hint: true,
        emptyTemplate:'没有历史记录： {{query}}',
        backdrop: {
            "background-color": "#fff"
        },
        template: '{{no}}<i expressId="{{expressId}}" class="fab icon-close typeahead-select-option" style="float: right;margin-right: 15px;margin-top: 2px;"></i>',
        source: {
            user: {
                display: "no",
                ajax: function (query) {
                    return {
                        type: "POST",
                        url: "user/expressNoCache",
                        path: "data",   //指定返回的json数据的哪个键为数据源
                    }
                }
            }
        },
        callback:function () {
            event.stopPropagation();
        }

    });

    /**
     * @author p1n93r
     * 点击删除历史搜索项
     */
    $(document).on("click", ".typeahead-select-option", function () {
        var id=$(this).attr("expressId");
        //ajax发送请求，删除此id对应的记录
        $.post('user/deleteUserExpressById',{id:id},function (data) {
            if (data.result){
                var deleteRes = '<div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>成功！已删除此记录！</div>';
                $("#express-form").find(".messages").html(deleteRes);
            }
        });
    });


    /**
     * @author p1n93r
     * 显示需求方订单表格
     */
    function doTable(url,queryData){
        // alert(url);
        $('#demandSideTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#demandSideTable").bootstrapTable({ // 对应table标签的id
            url:url, // 获取表格数据的url
            method: "post",
            contentType:'application/json',
            cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
            striped: true,  //表格显示条纹，默认为false
            pagination: true, // 在表格底部显示分页组件，默认false
            pageList: [5, 10], // 设置每页可显示的数据条数
            pageSize: 5, // 页面数据条数
            pageNumber: 1, // 首页页码
            sidePagination: 'client', // 设置为客户端分页
            sortable: true,          //列排序
            sortName: 'demandId', // 要排序的字段
            sortOrder: 'asc', // 排序规则
            toolbar: '#toolbar',
            search: true,
            //要发送的分页数据
            queryParams:function(params){
                //alert(JSON.stringify(queryData));
                var param = {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号(0开始)
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                };
                if(!jQuery.isEmptyObject(queryData)){
                    $.each(queryData,function(key,value){
                        param[key]=value;
                    });
                    //alert(JSON.stringify(param));
                }
                // alert(JSON.stringify(param));
                return param;
            },
            columns:
                [
                {
					field: 'demandId', // 返回json数据中的name
					title: '需求表ID', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					visible: false, //隐藏id
                    }, {
                    field: 'orderNumber',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'price',
                    title: '支付金额',
                    align: 'center',
                    valign: 'middle',
                    width: '80',
                    formatter:function(value, row, index){
                        //对value进行判断，返回html
                        return value+" 元";
                    }
                }, {
                    field: 'createTime',
                    title: '接单时间',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                },{
                    field: 'returnTime',
                    title: '退单时间',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'who',
                    title: '代取人',
                    align: 'center',
                    valign: 'middle',
                    width: '80',
                }, {
                    field: 'desc',
                    title: '备注',
                    align: 'center',
                    valign: 'middle',
                    width: '160',

                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    width: '80',
                    formatter:function(value, row, index){
                        //对value进行判断，返回html
                        var tag;
                        if(value=="0"){
                            tag="待接单";
                        }else if(value=="1"||value=="2"||value=="3"){
                            tag="待送达";
                        }else if(value=="4"){
                            tag="已送达";
                        }else if(value=="5"){
                            tag="已完结";
                        }else if(value=="6"){
                            tag="已退回";
                        }
                        return tag;
                    }
                }, {
                    field: 'status',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                    formatter:function(value, row, index){
                        //对value进行判断，返回html
                        var tag;
                        if(value=="0"){
                            tag="待接单";
                        }else if(value=="1"||value=="2"||value=="3"){
                            tag="待送达";
                        }else if(value=="4"){
                            tag="已送达";
                        }else if(value=="5"){
                            tag="已完结";
                        }else if(value=="6"){
                            tag="已退回";
                        }
                        var html="<button type=\"button\" class=\"btn btn-primary\">"+tag+"</button>";
                        return html;
                    }
                }
                ],
            //加载失败时执行
            onLoadError: function(res){
                //console.info("加载数据失败"+res);
                var prePostTips = '<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>错误！请重试...</div>';
                $("#demandSideOrder").find(".messages").html(prePostTips);
            },
            onLoadSuccess: function (data) {
                $("#demandSideOrder").find(".messages").html("");
            }


        });
        //刷新
        //$("#demandSideTable").bootstrapTable('refresh');
    }
    //查看所有退货申请的接口
    doTable("user/getDemandOrder");


    /**
     * @author p1n93r
     * 为需求方订单表格的筛选条件按钮添加active样式
     */
    $("#demandOrderBtnGroup .btn-border-light-blackborder").click(function () {

        if(!$(this).attr("isRefresh")){
            //先清除
            $("#demandOrderBtnGroup").children().removeClass("btn-border-light-active");
            //再添加
            $(this).addClass("btn-border-light-active");
        }
    })


    /**
     * @author p1n93r
     * 一些筛选按钮的处理
     */
    $("#demandOrderBtnGroup .btn:last").click(function (v) {
        //重新发送数据从而刷新表格
        $("#demandSideTable").bootstrapTable('refresh');
    });







    // $("#postQueryExpress").click(function () {
    //     alert("xsxs你点击了我");
    // });

    $(function() {
        $("#contact-form").validator();
        $("#contact-form").on("submit",
            function(a) {
                if (!a.isDefaultPrevented()) {
                    var url = "user/userPutFeedback";
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: $(this).serialize(),
                        success: function(d) {
                            //显示提示信息之前，先把原来的提示信息清除
                            $("#contact").find(".messages").html("");
                            var tag="成功！请关注邮件通知...";
                            var alert="success";
                            if(!d.result){
                                tag="失败！请查看邮件是否存在...";
                                alert="danger";
                            }
                            var prePostTips = '<div class="alert alert-'+alert+' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+tag+'</div>';
                            $("#contact").find(".messages").html(prePostTips);
                        },
                        error:function (v) {
                            $("#contact").find(".messages").html("");
                            var prePostTips = '<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>错误！请重试...</div>';
                            $("#contact").find(".messages").html(prePostTips);
                        }
                    });
                    return false
                }
            })
    });

});

//解决ajax重定向问题
$(document).ajaxComplete(function (event, xhr, settings) {
    // console.log("ajaxComplete ");
    redirectHandle(xhr);
});
function redirectHandle(xhr) {
    //获取后台返回的参数
    var url = xhr.getResponseHeader("redirectUrl");
    console.log("redirectUrl = " + url);
    var enable = xhr.getResponseHeader("enableRedirect");
    if((enable == "true") && (url != "")){
        var win = window;
        while(win != win.top){
            win = win.top;
        }
        win.location.href = url;
    }
}