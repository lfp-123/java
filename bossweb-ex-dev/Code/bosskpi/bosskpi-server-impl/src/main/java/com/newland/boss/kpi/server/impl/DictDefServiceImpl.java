package com.newland.boss.kpi.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;

/**
 * @author jwk
 */
@Component
public class DictDefServiceImpl implements DictDefService {

    @Autowired
    private DictDefDao dictDefDao;
    private static final Object LOCK = new Object();
    /**
     * 数据字典缓存对象
     */
    private static volatile Map<Integer, List<DictDef>> dictMapCache = null;

    /**
     * 查询字典中所有内容
     *
     * @return List<DictDef>
     */
    public List<DictDef> selectAllDictDef() {
        checkCache();
        List<DictDef> dictDefs = new ArrayList<>();
        for (Map.Entry<Integer, List<DictDef>> entry : dictMapCache.entrySet()) {
            List<DictDef> value = entry.getValue();
            dictDefs.addAll(value);
        }
        return dictDefs;
    }

    /**
     * 检查缓存对象
     */
    private void checkCache() {
        if (dictMapCache == null) {
            synchronized (LOCK) {
                if (dictMapCache == null) {
                    initCache();
                }
            }
        }
    }

    /**
     * 初始化数据字典缓存
     */
    public void initCache() {
        Map<Integer, List<DictDef>> dictMapCacheTemp = new HashMap<>();
        List<DictDef> dictDefs = dictDefDao.selectAllDictDef();
        for (DictDef dictDef : dictDefs) {
            Integer dictClass = dictDef.getDictClass();
            if (dictMapCacheTemp.containsKey(dictClass)) {
                List<DictDef> dictDefs1 = dictMapCacheTemp.get(dictClass);
                dictDefs1.add(dictDef);
            } else {
                List<DictDef> dictDefs1 = new ArrayList<>();
                dictDefs1.add(dictDef);
                dictMapCacheTemp.put(dictClass, dictDefs1);
            }
        }
        dictMapCache = dictMapCacheTemp;
    }

    /**
     * 根据字典类别和字典条目ID查询字典条目名称
     *
     * @param dictClass 字典类别
     * @param entryId   字典条目id
     * @return 字典条目名称
     */
    public String getEntryNameByDictClassAndEntryId(Integer dictClass, Integer entryId) {
        checkCache();
        List<DictDef> dictDefs = dictMapCache.get(dictClass);
        for (DictDef dictDef : dictDefs) {
            if (entryId.equals(dictDef.getEntryId())) {
                return dictDef.getEntryName();
            }
        }
        return null;
    }

    /**
     * 查询一个类数据字典下的所有定义
     *
     * @param dictClass 字典类别
     * @return list<字典表>
     */
    public List<DictDef> getDictDefByDictClass(Integer dictClass) {
        checkCache();
        return dictMapCache.get(dictClass);
    }

    /**
     * 根据字典类别和字典条目ID查询字典条目定义javabean
     *
     * @param dictClass 字典类别
     * @param entryId   字典条目id
     * @return 字典条目定义javabean
     */
    public DictDef getDictDefByDictClassAndEntryId(Integer dictClass, Integer entryId) {
        checkCache();
        List<DictDef> dictDefs = dictMapCache.get(dictClass);
        for (DictDef dictDef : dictDefs) {
            if (entryId.equals(dictDef.getEntryId())) {
                return dictDef;
            }
        }
        return null;
    }


}