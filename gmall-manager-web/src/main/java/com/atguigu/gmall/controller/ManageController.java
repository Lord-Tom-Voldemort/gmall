package com.atguigu.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0715.bean.BaseCatalog1;
import com.atguigu.gmall0715.bean.BaseCatalog2;
import com.atguigu.gmall0715.user.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    /**
     * 返回所有一级分类数据
     */
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();
    }

    /**
     * 返回二级分类数据
     */
    //http://localhost:8082/getCatalog2?catalog1Id=4
//    @RequestMapping("getCatalog2")
//    public List<BaseCatalog2> getCatalog2(String catalog1Id){
//        return manageService.getCatalog2(catalog1Id);
//    }
}
