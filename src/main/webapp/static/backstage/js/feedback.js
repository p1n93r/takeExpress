$(document).ready(function(){

    /**
     * @author cassie
     * 加载feedback数据，渲染到表格
     * 如果想隐藏某列：columnDefs:[{"targets":6,"visible":false}]
     */
    var table=$('.feedback-table').DataTable({
        pageLength: 10,
        responsive: true,
        retrieve: true,
        dom: '<"html5buttons"B>lTfgitp',
        //禁用排序，提高dom加载速度
        orderClasses: false,
        //dom: 'Bfrtip',
        buttons: [
            { extend: 'copy',title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6,7 ]
                }
            },
            {extend: 'csv',title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6,7 ]
                }
            },
            {extend: 'excel', title: '用户反馈信息',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6,7 ]
                }
            },
            {extend: 'pdfHtml5', title: '用户反馈信息',chartset:'utf-8',exportOptions: {
                    columns: [ 1, 2, 3, 4,5,6,7 ]
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
            }
        },
        //获取数据
        ajax: {
            "url": "backstage/findFeedback",
            "type": 'POST',
            //绑定额外参数
            "data": function (d) {
                // return $.extend({}, d,
                //     {
                //         "id":$("#user_id").val()
                //     });
            }
        },
        //设置隐藏列
        columnDefs:[
            {"targets":0,"visible":false},
            // {"targets":7,"width": "20%" },
            // {"targets":3,"width": "20%" },
            // {"targets":2,"width": "5%" }
        ],
        //延迟加载
        deferRender: true,
        autoWidth:false,
        //设置数据
        columns: [
            {data: "fid", defaltContent:0},
            {data: "loginId", defaultContent:"空用户"},
            {data: "subject", defaultContent: "空主题"},
            {data: "content", defaultContent: "空内容",render:function (data) {
                    if(data===''||data==null){
                        return '<span class="label label-warning spanCenter">空内容</span>';
                    }
                    var html='<div style="width:100px;cursor:pointer;overflow: hidden;text-overflow: ellipsis;white-space:nowrap" data-toggle="popover" data-placement="auto top" data-content="'+data+'" title="反馈内容">'+data+'</div>';
                    return html;
                }
            },
            {data: "createTime", defaultContent: "未知时间"},
            {data: "email", defaultContent: "未知邮箱"},
            {data: "status", defaultContent: "未知状态",className : 'itemCenter',render:function (data) {
                    var tag="warning";
                    var tip="未处理";
                    if(data==1){
                        tag="success";
                        tip="已处理";
                    }
                    var span='<span class="label label-'+tag+'">'+tip+'</span>';
                    return span;
                }
            },
            {data: "reply", defaultContent: "还未回复...",render:function (data) {
                    if(data===''||data==null){
                        return '<span class="label label-warning spanCenter">空内容</span>';
                    }
                    var html='<div style="width:100px;cursor:pointer;overflow: hidden;text-overflow: ellipsis;white-space:nowrap" data-toggle="popover" data-placement="auto top" data-content="'+data+'" title="回复内容">'+data+'</div>';
                    return html;
                }
            },
            {data: "status", defaultContent: "未知",className : 'itemCenter',render:function (data) {
                    var tag="info";
                    var tip="回复";
                    if(data==1){
                        tag="danger";
                        tip="删除";
                    }
                    var btn="<button type=\"button\" class=\"btn btn-"+tag+" btn-xs\">"+tip+"</button>";
                    return btn;
                }
            }
        ]
    });
    table.on( 'draw', function () {
        //激活popover
        $('[data-toggle="popover"]').popover();
        //为“空内容添加样式”
        $(".spanCenter").parent().addClass("itemCenter");
    } );



    /**
     * @author cassie
     * 管理员点击feedback回复后弹出的回复框
     */
    $(document).on('click','.feedback-table .btn-info',function (e) {
        //获取当前点击feedback的fid
        var row = $(this).parent().parent();
        //此处传入的一定要是row，不能是table的行号，不然经过排序后就会数据不对
        var currentRowData=table.row(row).data();
        //console.debug(currentRowData);
        const fid = currentRowData.fid;

        //查看data数据
        console.log(currentRowData);

        //弹出alert
        swal({
                title:'用户反馈回复',
                text:'请填写你对用户提交的问题的处理结果~',
                type:'input',
                showCancelButton:true,
                confirmButtonColor:'#33ccff',
                closeOnConfirm: false,
                closeOnCancel: false,
                confirmButtonText:'提交',
                cancelButtonText:'取消',
                inputPlaceholder:'已处理，请关注反馈结果......',
                showLoaderOnConfirm:true
            },function(inputValue){
                //console.debug(inputValue);
                if(jQuery.trim(inputValue)!=''&&inputValue==false){
                    swal("取消回复", "你没有进行回复！", "info");
                    return;
                }
                if(inputValue==''){
                    swal.showInputError('你没有填写任何回复信息！');
                    return;
                }
                //发送ajax
                $.ajax({
                    type:"post",
                    url:'backstage/updateFeedback',
                    traditional: true,
                    data:{"fid":fid,"reply":inputValue},
                    success:function (data) {
                        if(data.result){
                            swal("回复成功!", "您已进行此用户反馈的回复！", "success");
                            //更新单元格数据
                            //更新回复内容列
                            currentRowData.reply=inputValue;
                            //更新状态列
                            currentRowData.status=1;
                            //更新表格数据缓存
                            table.row(row).data(currentRowData).draw(false);
                        }else{
                            swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                        }
                    },
                    error:function (data) {
                        swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                    }
                });
            }
        );



    });



    /**
     * @author cassie
     * 管理员点击feedbakc的删除弹出alert提示框
     */
    $(document).on('click','.feedback-table .btn-danger',function (e){
        //获取当前点击feedback的fid
        var row = $(this).parent().parent();
        //此处传入的一定要是row，不能是table的行号，不然经过排序后就会数据不对
        var currentRowData=table.row(row).data();
        //console.debug(currentRowData);
        const fid = currentRowData.fid;
        //console.debug(fid);
        //弹出alert
        swal({
            title:'是否删除',
            text:'该条反馈已被处理，请确认是否删除该条用户反馈~',
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
                    url:'backstage/deleteOneFeedback',
                    traditional: true,
                    data:{"fid":fid},
                    success:function (data) {
                        if(data.result){
                            swal("删除成功!", "你已删除此条用户反馈！", "success");
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
                swal("取消删除！", "此条用户反馈没有被删除！", "info");
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