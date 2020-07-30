package com.express.utils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
final public class EmailUtils {
    private static JavaMailSenderImpl sender;
    private static SimpleMailMessage message;
    static {
        message=new SimpleMailMessage();
        sender = new JavaMailSenderImpl();
        Properties properties=new Properties();
        //获取配置文件的path
        String path = EmailUtils.class.getClassLoader().getResource("email/email.properties").getPath();
        //现在需要读取配置文件：email/email.properties
        //现在利用输入流读取此配置文件
        //现在的IO资源只有一个输入流，将其放在try的小括号内，使用完毕后，可以自动进行资源回收
        try (FileInputStream inputStream=new FileInputStream(path);){
            properties.load(inputStream);
            message.setFrom(properties.getProperty("email.username"));
            sender.setHost(properties.getProperty("email.host"));
            sender.setProtocol(properties.getProperty("email.protocol"));
            sender.setPort(Integer.valueOf(properties.getProperty("email.port")));
            sender.setUsername(properties.getProperty("email.username"));
            sender.setPassword(properties.getProperty("email.password"));
            Properties val = new Properties();
            val.setProperty("mail.smtp.auth",properties.getProperty("email.auth"));
            val.setProperty("mail.smtp.timeout",properties.getProperty("email.timeout"));
            sender.setJavaMailProperties(val);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendEmail(String sendTo,String mailSubject,String mailText){
        message.setTo(sendTo);
        message.setSubject(mailSubject);
        message.setText(mailText);
        sender.send(message);
    }
}
