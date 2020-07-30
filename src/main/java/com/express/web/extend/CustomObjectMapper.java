package com.express.web.extend;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author P1n93r
 * 自定义的一个jsonObject，用于去除接收的json数据中存在的前后空格情况
 */
@Component
public class CustomObjectMapper extends ObjectMapper {
    /**
     * 重写构造器
     */
    public CustomObjectMapper(){
        /*
            调用ObjectMapper的registerModule方法添加扩展点
            此处使用匿名对象直接new SimpleModul添加扩展功能
        */
        registerModule(new SimpleModule() {
            {
                /*
                    注册对于String类型值对象的反序列化器
                    对于反序列化器直接new StdDeserializer的子类StdScalarDeserializer完成
                */
                addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
                    @Override
                    public String deserialize(JsonParser jp, DeserializationContext context) throws IOException {
                        return StringUtils.trim(jp.getValueAsString());
                    }
                });
                // ... 也可自定义其他类型序列化和反序列化器，例如：蛋疼的日期类型...
            }
        });
    }
}
