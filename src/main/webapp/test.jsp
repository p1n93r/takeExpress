<%--
  Created by IntelliJ IDEA.
  User: Cassie
  Date: 2020/4/20
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<html>
<head>
    <title>在线测试显示</title>
    <bp:basePath request="<%=request%>"/>
    <link rel="stylesheet" href="static/public/css/bootstrap.min.css" type="text/css" media="all" />
</head>
<body>
<center style="margin-top:50px">
    <h4>你的ip地址：<%=request.getRemoteAddr()%></h4><br/><br/>




    <!-- 按钮：用于打开模态框 -->
    <button id="creatOrderBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#createOrder">模拟下单</button>

    <!-- 模态框 -->
    <div class="modal fade" id="createOrder" data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">请支付宝扫码支付</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- 模态框主体 -->
                <div class="modal-body">
                    <div id="qrDiv" style="width: 300px;height: 300px"></div>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">支付完成</button>
                </div>
            </div>
        </div>
    </div>





</center>





<%--引入js----start--%>

<script src="static/public/js/jquery-1.12.3.min.js"></script>
<script src="static/public/js/bootstrap.min.js"></script>

<script>

    $(function() {
        //订单号
        var num;


        //当模态框关闭时，需要主动查询支付状态
        $('#createOrder').on('hidden.bs.modal',function() {
            $.ajax({
                type:"post",
                url:'alipayQuery',
                traditional: true,
                data:{"num":num},
                success:function (data) {
                    if(data.code=="10000"&&data.msg=="Success"){
                        console.log("完结订单："+num);
                        alert("支付成功！");
                    }
                },
                error:function (data) {
                    console.log("交易将被关闭");
                }
            });
        });

        //模态框配置
        $(document).on('click','#creatOrderBtn',function (e) {
            //动态加载模态框的主内容，显示支付宝付款界面
            $.ajax({
                type:"post",
                url:'alipayTest',
                traditional: true,
                data:{"money":"99"},
                success:function (data) {
                    if(data.result){
                        num=data.num;
                        var apiUrl='http://qr.liantu.com/api.php?text='+data.url;
                        //为模态框添加一个二维码图片
                        var img=$('<img src="'+apiUrl+'" width="300px" height="300px" class="img-responsive" alt="支付二维码">');
                        $("#createOrder #qrDiv").html(img);
                    }else{
                        alert("服务器故障，请重试");
                    }
                },
                error:function (data) {
                    alert("请求失败，请重试");
                }
            });
        });

    });





</script>

<%--引入js----end--%>

</body>
</html>
