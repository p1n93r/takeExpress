package com.express.web.extend;
import com.express.common.TakeExpressResult;
import com.express.utils.exception.CodeMsg;
import com.express.utils.exception.ExceptionResult;
import com.express.utils.exception.GlobalException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 处理全局异常抛出，当我们通过303验证框架后，如果输入的数据不符合验证设定的数据时，应该返回一个json回前端，而不是直接报400错
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 19:05
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    //该注解声明异常处理方法
    @ExceptionHandler(value=Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        //对于自定义异常的处理
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return ExceptionResult.error(ex.getCm());
            //对于绑定异常的处理，使用jsr303中的自定义注解抛出的异常属于绑定异常
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            /*取第一个异常*/
            ObjectError error = errors.get(0);
            /*获取异常信息*/
            String errorMsg = error.getDefaultMessage();
            return TakeExpressResult.build(400,errorMsg);
        } else {
            return ExceptionResult.error(CodeMsg.SERVER_ERROR);
        }
    }
}