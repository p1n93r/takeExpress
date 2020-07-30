## 系统介绍
- 基于SSM的快递代取系统
- 开发成员：fyzn、p1n93r、cassie、cyy
- 我是p1n93r,我实现的内容包括：支付、日志、快递查询、站点统计、意见反馈

## 运行截图
0x00 快递查询：  
![快递查询][p0]

0x01 日志：
![日志0][p1]

![日志1][p2]

0x02 统计：
![统计][p3]

0x03 用户反馈：
![用户反馈0][p4]

![用户反馈1][p5]

![用户反馈2][p6]

支付宝：

![支付0][p7]

![支付1][p8]

![支付2][p9]

![支付3][p10]

## 注意
**以下是团队开发中一些留言提醒,成员编写各自模块后的一些提醒事项放在了下面~**

- gitee创建了一个dev分支用于开发，我们推送代码都推送到dev分支
- 如果控制器写了@ResponseBody，返回的http响应报文的Content-Type为html/text，不是application/json,所以你前端想直接把得到的数据当成js的json对象来用的话，需要在你的控制器的@RequestMapper中的produce设置为："application/json;charset=utf-8"

## 工具类
- 邮件发送：EmailUtil.sendEmail("目标邮箱","邮件主题","邮件内容");

## 一些优化及扩展
***Notice:*** 在web.extend包下添加扩展  
（1） 扩展：  
1. CustomObjectMapper类是自定义的用于json消息转换器的objectMapper类，现在主要完成的扩展：去除json数据中的前后空格
2. CustomWebBindingInitializer类是一个全局的控制器参数绑定初始化器，现在主要完成的扩展：去除表单参数绑定中的数据的前后空格  

（2） 优化：  
- 控制器数据预处理：比如我User模块下的控制器需要获取当前登陆用户的数据，可以写一个BaseController，在此Base控制器中进行数据预处理，获取当前登陆用户的数据，然后写此模块下的控制器时可以继承BaseController来获取预处理数据。
- 还有一个预防xss的过滤器未完成


## 验证模块
1. 判断登录验证
* 用户的登录判断  
通过User_TOKEN获取token  
通过token获取用户登录信息json  
将json转为pojo对象，使用工具类JsonUtils  
如判断当前用户是否登录：  
  
    
    String token = (String) session.getAttribute("User_TOKEN")  
    String json = session.getAttribute(token)  
    UserLogin userlogin = JsonUtils.jsonToPojo(json,UserLogin.class);  
    <!-- 其中T代表获取的对象，我存入的登录对象是UserLogin，可以通过UserLogin的userid获取完整的user -->   
    <!-- 如果需要获取管理员的登录对象只需要将token名User_TOKEN换为Manager_TOKEN即可获得登录对象-->  
    <!-- 判断是否登录只需要判断获取的token是否为空就行，这里建议用框架StringUtils判断-->   
    
    
* 拦截器判断一些页面是否登录才能访问    
* 在包web下有一个包Interceptor，包下有两个类loginInterceptor和ManagerLoginInceptor  
前者是对用户进行拦截，在该类中重新实现了preHandle，表示访问页面前拦截
发布到服务器上时需要更改这里的地址
* 拦截器的配置  
    
        <!-- 在springMvc文件中配置-->  
        <mvc:interceptors>
            <mvc:interceptor>
                <!-- 在这个位置指定你要拦截访问的路径即可，如我访问注册页面前需要验证是否登录如下配置即可，path里面多路径“,”隔开即可-->
                <mvc:mapping path="/registerView"/>
                <bean class="com.express.web.Interceptor.LoginInterceptor"/>
            </mvc:interceptor>
            <mvc:interceptor>
                <!-- 管理员访问后台前验证是否登录-->
                <mvc:mapping path="/admin/index"/>
                <bean class="com.express.web.Interceptor.ManagerLoginInterceptor"/>
            </mvc:interceptor>
        </mvc:interceptors>
        
## jsr303数据格式错误返回前端  
1. 保证系统能够不因一些错误而崩溃需要弄一个全局的异常抛出，类GlobalExceptionHandler还未编写完成,需要更改CodeMsg这个类异常信息抛出的status和msg  
2. 运用jsr303中的@Valid注解获取前端错误时，如果前端数据格式不正确，会抛出一个BindingException，这里的全局抛出异常还未完善，可以大家一起把遇到的一些特殊异常添加，这里不需要做什么，只需要导入这个类就会自动返回错误给前端，但是后台还是会打印出该异常  
        @NotNull(message = "管理员id不能为空")  
        private String managerid;  
         <!-- 上面的message通过全局异常类会将该消息返回给前端，这里是返回我自定义的类TakeExpressResult的一个对象json，在前端只需要判断状态码status==400，即可，是就alert后台msg即可-->  
           
        
## User表状态说明
0代表权限正常1表示被限制需求发布2代表代取权限被限制3代表所有权限被限制

## 使用dataTable插件时后台运用PageHelper进行分页

*  返回前端的数据，曹淦设置了返回前端的文件是已json的形式返回，这里可以直接返回一个对象  
在包common下的类DataTableResult，设置为返回前端的类型  
    如控制层如下  
```   
       @RequestMapping(value="/scantionUserShow",produces = "application/json;charset=utf-8")
       @ResponseBody
       public DataTableResult showApplyScantionUser(@RequestParam(defaultValue = "1") Integer start, @RequestParam(defaultValue = "10") Integer length, Integer draw,HttpServletRequest request){
           //之所以要用request方式接收前端传回的search参数，是因为前端传回的参数名为 search[value]
           //很难直接定义String search[value]
           String search = request.getParameter("search[value]");
           DataTableResult dataTableResult = manageUserService.ScantionUser(start,length,search);
           dataTableResult.setDraw(draw);
           dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
           return dataTableResult;
       }
```
在这里dataTable返回数据必须设置的参数setDraw(draw)，setRecordsFiltered，而参数search是为了接收前端提供搜索设置的参数  
  
  service层只需要初始化PageHelper，设置Data、RecordsTotal当然也可以将控制层设置的setRecordsFiltered在service层设置，但是不能缺少，否者分页失效  
```  
       @Override
          public DataTableResult registerUser(int start, int length,String search) {
              //进行分页处理
              //初始化分页插件
              PageHelper.startPage(start/length+1,length);
              //执行查询
              RegisterExample registerExample = new RegisterExample();
              if (StringUtils.isNotEmpty(search)) {
                  //执行查询
                  registerExample.createCriteria().andLoginIdLike("%"+search+"%");
              }
              //去除重复的行
              registerExample.setDistinct(false);
              List<Register> list = registerMapper.selectByExample(registerExample);
              //取分页信息
              PageInfo<Register> pageInfo = new PageInfo<>(list);
              //取分页信息,这里必须经过PageInfo初始化
              DataTableResult result = new DataTableResult();
              result.setData(pageInfo.getList());
              JurisdictionapplicationExample jurisdictionapplicationExample1 = new JurisdictionapplicationExample();
               result.setRecordsTotal((int) jurisdictionapplicationMapper.countByExample(jurisdictionapplicationExample1));
              return result;
          }

```    
* 普通页面分页可以初始化data集合以及pageInfo参数具体步骤如下  
```$xslt
    1. 初始化PageHelper  
    如PageHelper.startPage(start/length+1,length);  
    但值得注意的是，如果前端是通过pageInfo传回的参数时，那start就是具体的那一页，无需再进行运算，如下  
    PageHelper.startPage(start,length);   
    2. 执行查询语句获取对象数组  
    3. 初始化PageInfo  
    如  PageInfo<Register> pageInfo = new PageInfo<>(list);  
    4. 初始化data集合以及pageInfo参数  
    如  DataTableResult result = new DataTableResult();
        result.setData(pageInfo.getList());
        result.setPageInfo(pageInfo);  
    5. 返回前端
    
```  
* 对于前端运用dataTable框架时，jsp模板可以参照backstage下的jsp

## 面包屑的实现  
在每个子页面开始加上如下代码：  
```
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <ol class="breadcrumb">
            <li>
                <a >Home</a>
            </li>
            <li>
                <a>后台用户管理</a>
            </li>
            <li class="active">
                <strong>审核用户权限申请</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2"></div>
</div>

```
只需要修改li下的相应数据就行。 
 
## ajax提交请求的重定向  
1. 后台实现具体看controller下admin包下ShowAdminPageController的方法说明已经继承类的实现。
2. 前端具体我使用的是全局ajax的处理  
```
        $(document).ajaxComplete(function (event, xhr, settings) {
            console.log("ajaxComplete ")
            redirectHandle(xhr);
        })
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
``` 
该js目前还在index.jsp里

## 支付模块
***Notice：*** 已完结支付模块，并写好了demo。

- 控制器编写：com/express/web/controller/test/TestController.java
- 前端调用控制器：src/main/webapp/test.jsp
- 商家账号：xxcckt8859@sandbox.com，商户UID：2088102180921046，登录密码：111111
- 买家账号：ebsivy5168@sandbox.com，登录密码：111111，支付密码：111111
- 以上两个账号需要在沙箱支付宝app才能进行登录，登录买家账号可以打开test.jsp进行扫码支付，成功会alert一下，否则就算关闭交易




[p0]:./readme/快递查询.png
[p1]:./readme/日志0.png
[p2]:./readme/日志1.png
[p3]:./readme/统计.png
[p4]:./readme/用户反馈0.png
[p5]:./readme/用户反馈1.png
[p6]:./readme/用户反馈2.jpg
[p7]:./readme/支付0.png
[p8]:./readme/支付1.png
[p9]:./readme/支付2.jpg
[p10]:./readme/支付3.jpg



    
  

  
   
