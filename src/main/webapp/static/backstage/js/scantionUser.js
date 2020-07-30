
$(function () {

    $('.just-table').DataTable({
        pageLength: 10,
        responsive: true,
        retrieve: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            { extend: 'copy',title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5 ]
                }
            },
            {extend: 'csv',title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5 ]
                }
            },
            {extend: 'excel', title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6 ]
                }
            },
            {extend: 'pdfHtml5', title: '用户反馈信息',chartset:'utf-8',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6 ]
                }
            },
            //打印
            {extend: 'print',
                customize: function (win){
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');
                    $(win.document.body).find('table').addClass('compact').css('font-size', 'inherit');
                }
            }
        ],
        language: {
            "decimal": "",//小数的小数位符号  比如“，”作为数字的小数位符号
            "emptyTable": "没有数据哟~~",//没有数据时要显示的字符串
            "info": "当前 _START_ 条到 _END_ 条 共 _TOTAL_ 条",//左下角的信息，变量可以自定义，到官网详细查看
            "infoEmpty": "无记录",//当没有数据时，左下角的信息
            "infoFiltered": "(从 _MAX_ 条记录过滤)",//当表格过滤的时候，将此字符串附加到主要信息
            "infoPostFix": "",//在摘要信息后继续追加的字符串
            "thousands": ",",//千分位分隔符
            "lengthMenu": "每页 _MENU_ 条记录",//用来描述分页长度选项的字符串
            "loadingRecords": "加载中请稍等......",//用来描述数据在加载中等待的提示字符串 - 当异步读取数据的时候显示
            "processing": "处理中...",//用来描述加载进度的字符串
            "search": "搜索",//用来描述搜索输入框的字符串
            "zeroRecords": "没有找到",//当没有搜索到结果时，显示
            "paginate": {
                "first": "首页",
                "previous": "上一页",
                "next": "下一页",
                "last": "尾页"
            }
        },
        processing: true,//是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
        lengthChange: true,//是否允许用户改变表格每页显示的记录数
        orderMulti: true,  //启用多列排序
        ordering: true,//使用排序
        bStateSave: false,//记录cookie
        paging: true,//是否分页
        pagingType: "full_numbers",//除首页、上一·页、下一页、末页四个按钮还有页数按钮
        searching: true,//是否开始本地搜索
        stateSave: false,//刷新时是否保存状态
        autoWidth: true,//自动计算宽度
        deferRender: false,//延迟渲染
        serverSide: true,//开启服务器模式
        //获取数据
        ajax: {
            "url": "backstage/scantionUserShow",
            "type": 'POST',
            //绑定额外参数
            "data": function (d) {
                return $.extend({}, d,
                    {
                        "name":$("#name").val()
                    });
            }
        },
        //设置隐藏列
        columnDefs:[{"stuPic":0,"visible":false}],
        //设置数据
        columns: [
            {data: "userId", defaltContent:0},
            {data: "name", defaultContent:"空用户"},
            {data: "demandScore", defaultContent: "空主题",render:function (demandScore) {
                    if (demandScore<60){
                        return '<span class="label label-danger spanCenter demandScore">'+demandScore+"</span>"
                    }
                    return demandScore;
                }},
            {data: "fetchScore", defaultContent: "空内容",render:function (fetchScore) {
                    if (fetchScore<60){
                        return '<span class="label label-danger spanCenter fetchScore">'+fetchScore+"</span>"
                    }
                    return fetchScore;
                }},
            {data: "status", defaultContent: "未知",className : 'itemCenter',render:function (status) {
                    return status==0? "<span class=\"label label-success spanCenter type\">信誉良好</span>":status==1? "<span class=\"label label-warning spanCenter\">发布需求已被限制</span>":status==2? "<span class=\"label label-warning spanCenter\">代取权限已被限制</span>":status==3? "<span class=\"label label-success spanCenter\">所有权限已被限制</span>":"数据错误";
                }
            },
            {render:function (data) {
                    return'<span class="information" style="color:white"><a href="javascript:void(0)" onclick="edit(event,'+ data +')"><i class="fa fa-edit"></i>&nbsp;用户详情</a></span >';
                }
            }
        ]
    });
})
function edit(event,id){
    $('#userinfo').modal({
        backdrop:'static'//若不加backdrop会影响模态框界面拖动
    });
    var table=$('.just-table').off().on('click','tr',function(){
        var table = $(".just-table").DataTable();
        var num = table.row(this).index();//行索引
        var data = table.row(this).data();//获取数据
        var row = $(this).parent().parent();
        $("#userId").val(data.userId);
        $("#name").val(data.name);
        $("#demandScore").val(data.demandScore);
        $("#fetchScore").val(data.fetchScore);
        $("#type").val(data.status==0?"信誉良好":data.status==1?"发布需求已被限制":data.status==2?"代取权限已被限制":data.status==3?"所有权限已被限制":"");
        $("#stupic").attr("src",data.stuPic);

        $(".information").each(function (i) {
            $(this).attr('cust_index', i);
        })
        $(".type").each(function (i) {
            $(this).attr('cust_index', i);
        })
        $("#scantion-submit").click(function () {
            var userid = $("#userId").val();
            var select = $("#sell option:selected").val();
            var content = $("#textarea").val();
            $.ajax({
                type:"POST",
                data:{"userid":userid,"status":select,"content":content},
                url:"./backstage/sanctiosUser",
                async:false,
                success:function (data) {
                    if (data.status == 200 || data.status == 501){
                        //修改按钮的值
                        $('.information[cust_index='+num+']').html("<span class=\"label label-success\" onclick=\"edit(event,'+ data +')\">已制裁</span>");
                        //更新表格数据
                        $('.type[cust_index='+num+']').removeClass('label label-warning label-success ');
                        if(select==1){
                            $('.type[cust_index='+num+']').html("<span class=\"label label-warning\" >发布需求已被限制</span>");
                        }else if (select==2){
                            $('.type[cust_index='+num+']').html("<span class=\"label label-warning\">代取权限已被限制</span>");
                        } else if (select==3){
                            $('.type[cust_index='+num+']').html("<span class=\"label label-success\">所有权限已被限制</span>");
                        }
                        if (data.status==501){
                            swal('用户邮箱不存在','请继续操作','success');
                            $("#closemodify").click();
                            return true;
                        }
                        swal('制裁成功','请继续操作','success');
                        $("#closemodify").click();
                        return true;
                    }else {
                        var msg = data.msg
                        swal(msg,"重新操作",'error');
                    }
                    return true;
                }
            });
            return true;
        })
    });
}
$(function () {
    // 关闭模态框时还原所有数据
    $('.modal').on("hidden.bs.modal", function (e) {
        // window.location.reload(); 刷新页面
        $(this).removeData("bs.modal");
        document.getElementById("form").reset();
        $(".showTexArea").hide();//隐藏textarea
    });
    $('.sell').on('click', function () {
        $(".showTexArea").show();
    });

});
//没有起效，找不到搜索框的位置
$(this).parent().parent().find("input[type=\"search\"]").attr("id","search-byName");
$("input[type=\"search\"]").attr("onmousemove","mouseMove();");
function mouseMove(){
    $("#search-byName").tips({
        side : 1,
        msg : '为了更有体验感请按姓名进行查询或模糊查询',
        bg : '#AE81FF',
        time : 3
    });
    $("#search-byName").focus();
    return true;
}
/**
 * 一些其他本页面的组件需要用到的js
 */
$('.collapse-link').on('click', function () {
    var ibox = $(this).closest('div.ibox');
    var button = $(this).find('i');
    var content = ibox.children('.ibox-content');
    content.slideToggle(200);
    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    ibox.toggleClass('').toggleClass('border-bottom');
    setTimeout(function () {
        ibox.resize();
        ibox.find('[id^=map-]').resize();
    }, 50);
});
// Close ibox function
$('.close-link').on('click', function () {
    var content = $(this).closest('div.ibox');
    content.remove();
});

