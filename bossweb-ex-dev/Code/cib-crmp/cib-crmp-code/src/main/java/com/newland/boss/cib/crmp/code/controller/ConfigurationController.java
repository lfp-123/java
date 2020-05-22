package com.newland.boss.cib.crmp.code.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.newland.boss.cib.crmp.code.entity.AppConfig;
import com.newland.boss.cib.crmp.code.entity.BaseAppConfig;
import com.newland.boss.cib.crmp.code.entity.Dict;
import com.newland.boss.cib.crmp.code.entity.SearchConfig;
import com.newland.boss.cib.crmp.code.service.impl.AppConfigServiceImpl;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationEvent;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: configurationController
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 9:15
 */
@RestController
public class ConfigurationController {

    @Autowired
    private AppConfigServiceImpl appConfigService;

    //查询所有配置文件
    @PostMapping(value = "/showAllApp",produces = "text/json;charset=UTF-8")
    public String showAllApp(){
        List<AppConfig> allAppConfig = appConfigService.findAllAppConfig();
        String respJson;
        Gson gson = new Gson();
        respJson = gson.toJson(allAppConfig);
        return respJson;
    }

    //查询下拉框信息
    @PostMapping(value = "/showDictTree",produces = "text/json;charset=UTF-8")
    public String showDictTree(){
        List<Dict> dictTree = appConfigService.findDictTree();
        String listJson;
        Gson gson = new Gson();
        listJson = gson.toJson(dictTree);
        return listJson;
    }
    @PostMapping(value = "/showDictTypeTree",produces = "text/json;charset=UTF-8")
    public String showDictTypeTree(){
        List<Dict> dict = appConfigService.showDictTypeTree();
        String listJson;
        Gson gson = new Gson();
        listJson =  gson.toJson(dict);
        return listJson;
    }

    //新增配置
    @PostMapping(value = "/addConfig",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String  addConfig(@RequestBody AppConfig config) {
        appConfigService.addConfig(config);
        return null;
    }

    //删除配置
    @PostMapping(value = "/deleteAppConfig",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public void  deleteAppConfig(@RequestBody List<BaseAppConfig> appConfigs){
        appConfigService.deleteAppConfig(appConfigs);
    }

    //模糊查询
    @RequestMapping(value = "/searchConfig", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String searchRole(@RequestBody SearchConfig config){
       if(config.getSystemId() ==null || config.getSystemId()=="" || config.getSystemId().equals("null")){
           config.setSystemId(null);
       }
        List<BaseAppConfig> roleList = appConfigService.searchConfig(config.getSystemId(),config.getAppName(),config.getAppInstanceName());
        return JSONArray.toJSONString(roleList);
    }

    //修改配置信息
    @PostMapping(value = "/editConfig",produces = "text/json;charset=UTF-8")
    public void editConfig(@RequestBody AppConfig config){
        appConfigService.editConfig(config,config.getConfigSeq());
    }
}
