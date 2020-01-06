package com.atguigu.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0715.bean.*;
import com.atguigu.gmall0715.user.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();
    }

    /**
     * 返回二级分类数据
     */
    //http://localhost:8082/getCatalog2?catalog1Id=4
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id,BaseCatalog2 baseCatalog2){
        //return manageService.getCatalog2(catalog1Id);
        return manageService.getCatalog2(baseCatalog2);
    }

    /**
     * 返回三级目录
     * http://localhost:8082/getCatalog3?catalog2Id=5
     * @param catalog2Id
     * @param baseCatalog3
     * @return
     */
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id, BaseCatalog3 baseCatalog3){
        //return manageService.getCatalog2(catalog1Id);
        return manageService.getCatalog3(baseCatalog3);
    }
    /**
     * 根据三级分类id查询列表
     * http://localhost:8082/attrInfoList?catalog3Id=109
     */
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(String catalog3Id,BaseAttrInfo baseAttrInfo){

        //return manageService.getAttrInfoList(baseAttrInfo);
        return manageService.getAttrInfoList(catalog3Id);
    }

    /**
     * 添加平台属性值
     * http://localhost:8082/saveAttrInfo
     */
    @RequestMapping("saveAttrInfo")
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
            manageService.saveAttrInfo(baseAttrInfo);
    }

    /**
     * 获取属性平台值
     * http://localhost:8082/getAttrValueList?attrId=37
     */
    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        //功能
        //return manageService.getAttrValueList(attrId);
        //业务
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
//        if (baseAttrInfo == null){
//            return null;
//        }
        return baseAttrInfo.getAttrValueList();
    }


}
