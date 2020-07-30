
$(function () {

    $('.register-table').DataTable({
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
        bStateSave: true,//记录cookie
        paging: true,//是否分页
        pagingType: "full_numbers",//除首页、上一·页、下一页、末页四个按钮还有页数按钮
        //searching: false,//是否开始本地搜索
        stateSave: false,//刷新时是否保存状态
        autoWidth: true,//自动计算宽度
        //deferRender: true,//延迟渲染
        serverSide: true,//开启服务器模式
        //获取数据
        ajax: {
            "url": "backstage/managerRegister",
            "type": 'POST',
            //绑定额外参数
            "data": function (d) {
                return $.extend({}, d,
                    {
                        "loginId":$("#name").val()
                    });
            }
        },
        //设置隐藏列
        columnDefs:[
            {"stuPic":0,"visible":false},
            {"loginId":0,"visible":false},
        ],
        //设置数据
        columns: [
            {data: "registerId", defaltContent:0},
            {data: "phone", defaultContent:"空用户"},
            {data: "email", defaultContent: "空主题"},
            {data: "loginId", defaultContent: "空用户",className : 'itemCenter loginid-color'},
            {render:function (data) {

                    return'<button  class="btn  btn-light btn-xs register-id" id="register-id" style="color: white" ><a href="javascript:void(0)" onclick="edit(event,'+ data +')"><i class="fa fa-edit"></i>&nbsp;审核</a></button >';
                }
            }
        ]
    });
})
function edit(event,id){
    $('#Register-scantion').modal({
        backdrop:'static'//若不加backdrop会影响模态框界面拖动
    });
    var table=$('.register-table').off().on('click','tr',function(){
        var table = $(".register-table").DataTable();
        var num = table.row(this).index();//行索引
        var data = table.row(this).data();//获取数据
        var row = $(this).parent().parent();
        $("#registerId").val(data.registerId);
        $("#loginId").val(data.loginId);
        $("#phone").val(data.phone);
        $("#email").val(data.email);
        $("#stuPic").attr("src",data.stuPic);
        /*为每一个按钮添加一个索引*/
        $('.register-id').each(function (i) {
            $(this).attr('cust_index', i);
        });
        $("#submit-scantion").click(function () {
            var registerid = $("#registerId").val();
            var content = $("#textareaInfo").val();
            var select= $("#sell option:selected").val();
            $.ajax({
                type:"POST",
                data:{"registerid":registerid,"status":select,"content":content},
                url:"./backstage/auditRegister",
                async:false,
                success:function (data) {
                    if (data.status==200 || data.status==500){
                        $('.register-id[cust_index='+num+']').html("<span class=\"label label-success\">已审核</span>");
                        if (data.status==500) {
                            var msg = data.msg;
                            swal(msg,"审核完成","success");
                            $("#closemodify").click();
                            return true;
                        }
                        swal("审核成功","请继续操作","success");
                        $("#closemodify").click();
                        return true;
                    }
                    swal("审核未通过","请继续操作","error");
                    return false;
                }
            });
            return false;
        });
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
        if ($(".sell option:selected").val()==1) {
            $(".showTexArea").show();
        }else {
            $(".showTexArea").hide();
        }
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

