package com.express.web.controller.backstage;


import com.express.service.manager.LogService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Cassie
 */
@RestController

public class LogMngController extends ManagerBaseController{
    @Resource
    LogService service;

    @RequestMapping(value = "/deleteOneLog",produces = "application/json;charset=utf-8")
    public String deleteOneLog(@RequestParam Integer logId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteOneLog(logId);
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }


    @RequestMapping(value = "/deleteSomeLog",produces = "application/json;charset=utf-8")
    public String deleteSomeLog(@RequestParam List<Integer> logId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteSomeLog(logId);
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @RequestMapping(value = "/deleteAllLog",produces = "application/json;charset=utf-8")
    public String deleteAllLog(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteAllLog();
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/findAllLog",produces = "application/json;charset=utf-8")
    public String findAllLog(){
        JSONObject jsonObject = new JSONObject();
        try {
            String s = service.selectAllLog();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deleteOneLogOperation",produces = "application/json;charset=utf-8")
    public String deleteOneLogOperation(@RequestParam Integer logOpid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteOneLogOperation(logOpid);
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deleteSomeLogOperation",produces = "application/json;charset=utf-8")
    public String deleteSomeLogOperation(@RequestParam List<Integer> logOpid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteSomeLogOperation(logOpid);
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deleteAllLogOperation",produces = "application/json;charset=utf-8")
    public String deleteAllLogOperation(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            Integer integer = service.deleteAllLogOperation();
            if(integer>0){
                jsonObject.put("result",true);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/findAllLogOperation",produces = "application/json;charset=utf-8")
    public String findAllLogOperation(){
        JSONObject jsonObject = new JSONObject();
        try {
            String s = service.selectAllLogOperation();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }



}
