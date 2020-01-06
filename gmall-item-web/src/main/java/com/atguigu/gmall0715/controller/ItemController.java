package com.atguigu.gmall0715.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall0715.bean.SkuInfo;
import com.atguigu.gmall0715.bean.SkuSaleAttrValue;
import com.atguigu.gmall0715.bean.SpuSaleAttr;
import com.atguigu.gmall0715.user.service.ManageService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class ItemController {

    @Reference
    private ManageService manageService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable(value = "skuId") String skuId, HttpServletRequest request){
        System.out.println(skuId);

        SkuInfo skuInfo = manageService.getSkuInfo(skuId);

        //查询销售属性-销售属性值并锁定
        List<SpuSaleAttr> spuSaleAttrList = manageService.selectSpuSaleAttrListCheckBySku(skuInfo);

        //查询 销售属性和skuId组合的数据集合
        List<SkuSaleAttrValue> skuSaleAttrValueListBySpu = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());

        //拼接字符串
        String key = "";
        HashMap<String, String> map = new HashMap<>();
        if (skuSaleAttrValueListBySpu != null && skuSaleAttrValueListBySpu.size() > 0){
            for (int i = 0; i < skuSaleAttrValueListBySpu.size(); i++) {
                SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueListBySpu.get(i);
                //什么时候拼接
                if (key.length() > 0){
                    key += "|";

                }
                //拼接key
                key += skuSaleAttrValue.getSaleAttrValueId();
                //什么时候停止拼接
                if ((i +1) == skuSaleAttrValueListBySpu.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueListBySpu.get(i + 1).getSkuId())){
                    map.put(key,skuSaleAttrValue.getSkuId());
                    //清空key
                    key = "";
                }
            }
        }
        //map转换成json
        String valuesSkuJson = JSON.toJSONString(map);
        //保存到作用域
        request.setAttribute("valuesSkuJson",valuesSkuJson);
        request.setAttribute("spuSaleAttrList",spuSaleAttrList);
        //保存skuInfo
        request.setAttribute("skuInfo",skuInfo);
        return "item";
    }

}
