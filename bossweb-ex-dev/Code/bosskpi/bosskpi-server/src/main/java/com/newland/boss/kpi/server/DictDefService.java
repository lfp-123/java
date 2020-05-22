package com.newland.boss.kpi.server;

import com.newland.boss.kpi.entity.DictDef;

import java.util.List;

/**
 * 数据字段服务接口类
 *
 * @author jwk
 */
public interface DictDefService {

    /**
     * 查询字典中所有内容
     *
     * @return List<DictDef>
     */
    List<DictDef> selectAllDictDef();

    /**
     * 初始化
     */
    void initCache();

    /**
     * 根据字典类别和字典条目ID查询字典条目名称
     *
     * @param dictClass 字典类别
     * @param entryId   字典条目id
     * @return 字典条目名称
     */
    String getEntryNameByDictClassAndEntryId(Integer dictClass, Integer entryId);

    /**
     * 查询一个类数据字典下的所有定义
     *
     * @param dictClass 字典类别
     * @return list<字典表>
     */
    List<DictDef> getDictDefByDictClass(Integer dictClass);

    /**
     * 根据字典类别和字典条目ID查询字典条目定义javabean
     *
     * @param dictClass 字典类别
     * @param entryId   字典条目id
     * @return 字典条目定义javabean
     */
    DictDef getDictDefByDictClassAndEntryId(Integer dictClass, Integer entryId);

}
