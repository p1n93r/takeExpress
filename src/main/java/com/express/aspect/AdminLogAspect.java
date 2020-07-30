package com.express.aspect;


import com.express.common.TakeExpressResult;
import com.express.domain.Log;
import com.express.domain.LogOperation;
import com.express.mapper.LogMapper;
import com.express.mapper.LogOperationMapper;
import com.express.utils.IpUtils;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cassie
 */
@Aspect
@Component
public class AdminLogAspect {
    @Resource
    LogMapper logMapper;

    @Resource
    LogOperationMapper logOperationMapper;

    @Pointcut("execution(* com.express.service.impl.manager.ManagerLoginServiceImpl.*(..))")
    public void loginPointCut(){}

    /**
     * service里的添加操作作为切点
     */
    @Pointcut("execution(* com.express.service.impl.*manager*.*.add*(..))")
    public void logOperationAdd(){}

    /**
     * service里的删除操作作为切点
     */
    @Pointcut("execution(* com.express.service.impl.*manager*.*.delete*(..))")
    public void logOperationDelete(){}

    /**
     * service里的更新操作作为切点
     */
    @Pointcut("execution(* com.express.service.impl.*manager*.*.update*(..))")
    public void logOperationUpdate(){}





    /**
     * @param jp JoinPoint
     * @param object 切点的返回值
     */
    @AfterReturning(returning = "object",pointcut = "loginPointCut()")
    public void logAdvice(JoinPoint jp,Object object){
        //判断object的真实类型，然后将其转换为真实类型
        HashMap<String,String> map=null;
        TakeExpressResult takeExpressResult=null;
        if(object instanceof Map){
            map=(HashMap)object;
        }else if(object instanceof TakeExpressResult){
            takeExpressResult=(TakeExpressResult)object;
        }
        //现在通过获得的返回值，提取里面的判断登录状态
        Boolean isSuccess=false;
        if(map!=null){
            if(map.containsKey("status")){
                String status = map.get("status");
                if("1".equals(status)){
                    isSuccess=true;
                }
            }
        }else if(takeExpressResult!=null){
            Integer status = takeExpressResult.getStatus();
            if(status!=null){
                if(status.equals(200)){
                    isSuccess=true;
                }
            }
        }
        //实例化一个Log对象，并把这个对象插入数据库
        //要想获取客户端的ip，需要获取request对象，request对象在军军的service的形参里，通过JoinPoint获取
        //通过JoinPoint只能获取service的所有形参，并且是Object类型的，所以你需要判断是否是HttpServletRequest类型，然后找到他，拿来用
        //然后就是通过request对象获取客户端ip，通过ip调用工具类获取api的返回信息
        //将这个api返回的信息转换成json对象，然后获得这个json对象的两个键：province和city所对应的值，这就是我们需要的位置信息
        //吧这个位置信息放到Log对象的location属性中，然后设置好Log对象的其他属性
        //调用LogMapper插入这条Log
        //over完毕~~~~
        //自己写╭(╯^╰)╮！
        Log log = new Log();
        log.setLogTime(LocalDateTime.now().toString());
        log.setIsSuccess(isSuccess);
        Object[] args = jp.getArgs();
        String ip=null;
        for(Object v:args){
            if(v instanceof HttpServletRequest){
                ip=((HttpServletRequest) v).getRemoteAddr();
                break;
            }
        }
        log.setIp(ip);
        String s = IpUtils.queryIpAddress(ip);
        JSONObject jsonObject = JSONObject.fromObject(s);
        //
        Object status = jsonObject.get("status");
        if(status.equals(0)){
            JSONObject content = (JSONObject)jsonObject.get("content");
            //address：省+市
            String address = (String)content.get("address");
            //point：经纬度，x=经度，y=纬度
            JSONObject point = (JSONObject) content.get("point");
            String x = (String)point.get("x");
            String y = (String)point.get("y");
            address=address+";经度："+x+";纬度："+y;
            log.setLocation(address);
        }else {
            log.setLocation("找不到ip地址对应的具体地点");
        }
        logMapper.insert(log);

    }

    /**
     * 添加操作日志(后置通知)
     *
     * @param joinPoint
     */
      @AfterReturning("logOperationAdd()")
    public void insertLog(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null) {
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        LogOperation logOperation = new LogOperation();
        logOperation.setLogOptime(LocalDateTime.now().toString());
        logOperation.setLogOpcontent(opContent);
        logOperation.setLogOptype("增加");
        logOperationMapper.insert(logOperation);
    }

    /**
     * 操作：删除
     * @param joinPoint
     * @throws Throwable
     */
    @AfterReturning("logOperationDelete()")
    public void deleteLog(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null) {
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        LogOperation logOperation = new LogOperation();
        logOperation.setLogOptime(LocalDateTime.now().toString());
        logOperation.setLogOpcontent(opContent);
        logOperation.setLogOptype("删除");
        logOperationMapper.insert(logOperation);
    }

    /**
     * 操作：更新
     * @param joinPoint
     * @throws Throwable
     */
    @AfterReturning("logOperationUpdate()")
    public void updateLog(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null) {
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        LogOperation logOperation = new LogOperation();
        logOperation.setLogOptime(LocalDateTime.now().toString());
        logOperation.setLogOpcontent(opContent);
        logOperation.setLogOptype("更新");
        logOperationMapper.insert(logOperation);
    }



    /**
     * 获取切点方法名+形参+形参值
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String mName) {
        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 0;
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            rs.append("("+info.toString()+")");
            rs.append("]");
            index++;
        }
        return rs.toString();
    }




}
