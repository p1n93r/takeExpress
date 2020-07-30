$(document).ready(function(){

    /**
     * @author cassie
     * 加载feedback数据，渲染到表格
     * 如果想隐藏某列：columnDefs:[{"targets":6,"visible":false}]
     */
    var table=$('.loginLog-table').DataTable({
        pageLength: 10,
        responsive: true,
        retrieve: true,
        dom: '<"html5buttons"B>lTfgitp',
        //禁用排序，提高dom加载速度
        orderClasses: false,
        //dom: 'Bfrtip',
        //select扩展
        select: true,
        buttons: [
            { extend: 'copy',title: '管理员登录日志'},
            {extend: 'csv',title: '管理员登录日志'},
            {extend: 'excel', title: '管理员登录日志'},
            {extend: 'pdf', title: '管理员登录日志',charset:'utf-8',pageSize:'A4'},
            //打印
            {extend: 'print',
                customize: function (win){
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');
                    $(win.document.body).find('table').addClass('compact').css('font-size', 'inherit');
                }
            },
        ],
        pagingType: "full_numbers",
        //汉化
        language : {
            "decimal": "",//小数的小数位符号  比如“，”作为数字的小数位符号
            "emptyTable": "没有数据哟~~",//没有数据时要显示的字符串
            "info": "当前 _START_ 条到 _END_ 条 共 _TOTAL_ 条",//左下角的信息，变量可以自定义，到官网详细查看
            "infoEmpty": "无记录",//当没有数据时，左下角的信息
            "infoFiltered": "(从 _MAX_ 条记录过滤)",//当表格过滤的时候，将此字符串附加到主要信息
            "infoPostFix": "",//在摘要信息后继续追加的字符串
            "thousands": ",",//千分位分隔符
            "lengthMenu": "每页 _MENU_ 条记录",//用来描述分页长度选项的字符串
            "loadingRecords": "加载中...",//用来描述数据在加载中等待的提示字符串 - 当异步读取数据的时候显示
            "processing": "处理中...",//用来描述加载进度的字符串
            "search": "搜索",//用来描述搜索输入框的字符串
            "zeroRecords": "没有找到",//当没有搜索到结果时，显示
            "paginate": {
                "first": "首页",
                "previous": "上一页",
                "next": "下一页",
                "last": "尾页"
            },

        },
        //获取数据
        ajax: {
            "url": "backstage/findAllLog",
            "type": 'POST'
        },
        //延迟加载
        deferRender: true,
        autoWidth:false,
        //设置数据
        columns: [
            {data: "logId", defaltContent:0},
            {data: "logTime", defaultContent:"空内容"},
            {data: "ip", defaultContent: "空内容"},
            {data: "location", defaultContent: "空内容",render:function (data) {
                    if(data===''||data==null){
                        return '<span class="label label-warning spanCenter">空内容</span>';
                    }
                    var html='<div style="width: 250px;white-space:nowrap">'+data+'</div>';
                    // var html='<div style="width:200px;cursor:pointer;overflow: hidden;text-overflow: ellipsis;white-space:nowrap" data-toggle="popover" data-placement="auto top" data-content="'+data+'" title="反馈内容">'+data+'</div>';
                    return html;
                }
            },
            {data: "isSuccess", defaultContent: "未知状态",className : 'itemCenter',render:function (data) {
                    var tag="danger";
                    var tip="失败";
                    if(data==1){
                        tag="success";
                        tip="成功";
                    }
                    var span='<span class="label label-'+tag+'">'+tip+'</span>';
                    return span;
                }
            },
            {data: "isSuccess", defaultContent: "不可操作",className : 'itemCenter',render:function (data) {
                    tag="danger";
                    tip="删除";
                    var btn="<button type=\"button\" class=\"btn btn-"+tag+" btn-xs\">"+tip+"</button>";
                    return btn;
                }
            }
        ]
    });

    // table.buttons().container().appendTo($("#remove-some"));
    console.log(table.buttons().container());

    //设置表格可选行
    $(".loginLog-table tbody").on( 'click', 'tr', function () {
        $(this).toggleClass('danger');
    } );

    // $('#button').click( function () {
    //     alert( table.rows('.selected').data().length +' row(s) selected' );
    // });




    /**
     * @author cassie
     * 管理员点击feedbakc的删除弹出alert提示框
     */
    $(document).on('click','.loginLog-table .btn-danger',function (e){
        //获取当前点击feedback的fid
        var row = $(this).parent().parent();
        //此处传入的一定要是row，不能是table的行号，不然经过排序后就会数据不对
        var currentRowData=table.row(row).data();
        //console.debug(currentRowData);
        const logId = currentRowData.logId;
        //console.debug(fid);
        //弹出alert
        swal({
            title:'是否删除',
            text:'请确认是否删除该条登录日志',
            type:'warning',
            showCancelButton:true,
            confirmButtonColor:'#DD6B55',
            closeOnConfirm: false,
            closeOnCancel: false,
            confirmButtonText:'删除',
            cancelButtonText:'取消',
            showLoaderOnConfirm:true
        },function(is){
            if (is) {
                //调用ajax发送数据
                //发送ajax
                $.ajax({
                    type:"post",
                    url:'backstage/deleteOneLog',
                    traditional: true,
                    data:{"logId":logId},
                    success:function (data) {
                        if(data.result){
                            swal("删除成功!", "你已删除此条登录日志！", "success");
                            //需要刷新表格的内容
                            table.row(row).remove().draw(false);
                        }else{
                            swal("删除失败！", "回复失败，请检查网络配置后重试！", "error");
                        }
                    },
                    error:function (data) {
                        swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                    }
                });
            } else {
                swal("取消删除！", "此条登录日志没有被删除！", "info");
            }
        });

    });


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





});