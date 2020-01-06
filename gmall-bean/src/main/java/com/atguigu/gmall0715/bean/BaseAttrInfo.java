package com.atguigu.gmall0715.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class BaseAttrInfo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String attrName;

    @Column
    private String catalog3Id;

    @Transient //数据库中没有但业务中需要的字段
    private List<BaseAttrValue> attrValueList;

}
