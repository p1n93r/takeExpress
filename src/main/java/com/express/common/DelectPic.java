package com.express.common;

import java.io.File;

public class DelectPic {
    public static boolean delect(String path){
        try{
            File file = new File(path);
            //判断文件是否存在
            if (file.exists()){
                Boolean flag = false;
                flag = file.delete();
                if (flag){
                    System.out.println("删除文件成功");
                    return true;
                }else {
                    System.out.println("删除文件失败") ;
                    return false;
                }
            }else {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
