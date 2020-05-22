package com.newland.boss.kpi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weixc
 * 2018-05-18
 */
@RestController
public class DictDefController {
    private static Logger logger = LogManager.getLogger(DictDefController.class);

    @Autowired
    private DictDefService dictDefService;

    @RequestMapping(value = "/getDictDefByDictClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getDictDefByDictClass(@RequestBody DictDef dictDef) {
        List<DictDef> dictDefList = dictDefService.getDictDefByDictClass(dictDef.getDictClass());
        if (dictDefList == null) {
            return "";
        }
        return JSONArray.toJSONString(dictDefList);
    }

    @RequestMapping(value = "/getDictDefByDictClassAndEntryId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getDictDefByDictClassAndEntryId(@RequestBody DictDef dictDef) {
        DictDef dictDef1 = dictDefService.getDictDefByDictClassAndEntryId(dictDef.getDictClass(),dictDef.getEntryId());
        if (dictDef1 == null) {
            return "";
        }
        return JSONObject.toJSONString(dictDef1);
    }


}