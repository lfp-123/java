package com.newland.boss.cib.crmp.code.dao;

import com.newland.boss.cib.crmp.code.entity.Dict;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: DictTreeDao
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 22:19
 */
public interface DictTreeDao {
    List<Dict> findDictTree();
    List<Dict> showDictTypeTree();
}
