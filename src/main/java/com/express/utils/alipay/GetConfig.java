package com.express.utils.alipay;

import com.alipay.easysdk.kernel.BaseClient.Config;

import java.io.FileInputStream;
import java.util.Properties;


/**
 * 获取alipay的商家配置信息
 * @author Cassie
 */
public class GetConfig {

    private static Config config;

    static{
        Properties properties=new Properties();
        //获取配置文件的path
        String path = GetConfig.class.getClassLoader().getResource("api/alipay/alipay.properties").getPath();
        try (FileInputStream inputStream=new FileInputStream(path);){
            properties.load(inputStream);
            Config configTmp = new Config();
            configTmp.protocol=properties.getProperty("config.protocol");
            configTmp.gatewayHost=properties.getProperty("config.gatewayHost");
            configTmp.signType=properties.getProperty("config.signType");
            configTmp.appId=properties.getProperty("config.appId");
            configTmp.merchantPrivateKey=properties.getProperty("config.merchantPrivateKey");

            //设置异步回调url(采用主动监测订单，无需异步回调)
            //configTmp.notifyUrl=properties.getProperty("config.notifyUrl");

            //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
            configTmp.merchantCertPath = GetConfig.class.getClassLoader().getResource("api/alipay/appCertPublicKey_2016102400753062.crt").getPath();
            configTmp.alipayCertPath = GetConfig.class.getClassLoader().getResource("api/alipay/alipayCertPublicKey_RSA2.crt").getPath();
            configTmp.alipayRootCertPath = GetConfig.class.getClassLoader().getResource("api/alipay/alipayRootCert.crt").getPath();
            //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
            // config.alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";
            config=configTmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return 返回alipay的一个配置实例
     */
    public static Config get(){
        return config;
    }


}
