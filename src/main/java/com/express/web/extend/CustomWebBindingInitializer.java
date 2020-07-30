package com.express.web.extend;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;


/**
 * @author P1n93r
 * 对参数绑定进行扩展
 */
@Component
public class CustomWebBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        // 对字符串的参数绑定进行扩展，去除前后空格
        // 注册对于String类型参数对象的属性进行trim操作的编辑器,
        // 构造参数代表：空串是否转为null，true则将空串转为null
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

        //可以在此继续扩展~，比如扩展日期参数绑定
    }
}
