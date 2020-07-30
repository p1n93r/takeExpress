package com.express.service.impl;
import com.express.service.PictureService;
import com.express.utils.FileTypeVerification;
import com.express.utils.IDUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *PictureServiceImpl  class
 * @author fyzn12
 * @date 2020/03/16
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Override
    public Map<String,Object> uploadPicture(MultipartFile uploadFile , String path) {
        Map<String,Object>map = new HashMap<>(16);
        //判断上传文件是否为空
        //0 代表上传成功，1代表上传图片为空 2 代表不支持上传图片类型 3 上传图片失败
        if (null == uploadFile || uploadFile.isEmpty()) {
            map.put("status",1);
            return map;
        }
        //验证上传文件类型是否是图片，防止利用上传接口上传木马
        try {
           // ByteArrayInputStream  verification = (ByteArrayInputStream )uploadFile.getInputStream();//获得上传文件
            byte[] b = new byte[3];
            uploadFile.getInputStream().read(b, 0, b.length);
            //调用已经写好的验证工具，将文件头转为字符
            String photo = FileTypeVerification.bytesToHexString(b);
            //验证是否为图片类型，不是图片类型直接return
            if (!FileTypeVerification.checkFileType(photo)){
                map.put("status",2);
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取原始的图片的名称
        String originalFilename = uploadFile.getOriginalFilename();
        //获取图片的后缀名
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成新文件名
        //可以使用uuid生成新文件名。
        //UUID.randomUUID()
        //可以是时间+随机数生成文件名
        //条用文件名生成方法
        String imageName = IDUtils.genImageName();
        //文件在服务器的存放路径，应该使用日期分隔的目录结构
        DateTime dateTime = new DateTime();
        String name = dateTime.toString("yyyy-MM-dd-");
        String filePathName = path + path+"/"+name+imageName+ext;
        File saveFile=new File(path+"/"+name+imageName+ext);
        //判断是否存在图片的保存文件
        if (saveFile.getParentFile() != null || !saveFile.getParentFile().isDirectory()) {
            //创建文件
            saveFile.getParentFile().mkdirs();
        }
        long size = uploadFile.getSize();
        double quality = 1d;
        //当图片超过100kb的时候进行压缩处理
        if (size >= 100 * 1024) {
            if (size > 0) {
                quality = (100 * 1024f) / size;
            }
        }
        try {
            Thumbnails.of(uploadFile.getInputStream()).scale(1f).outputQuality(quality).toFile(saveFile);
            //将文件写入目录
            FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",3);
            return map;
        }
        int index = path.indexOf("static");
        //后面的字符串， +3 是key的长度为3 (KEY的值长度)
        String url = path.substring(index+6) ;
        map.put("status",0);
        map.put("url","static"+url+"\\"+name+imageName+ext);
        return map;
    }
}
