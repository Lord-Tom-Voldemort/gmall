package com.atguigu.gmall0715.user.service;

import com.atguigu.gmall0715.bean.BaseCatalog1;

import java.util.List;

public interface ManageService {
    /**
     * 查询一级分类商品
     * @return
     */
    List<BaseCatalog1> getCatalog1();
}
