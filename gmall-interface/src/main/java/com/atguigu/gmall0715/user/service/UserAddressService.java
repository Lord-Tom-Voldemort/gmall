package com.atguigu.gmall0715.user.service;

import com.atguigu.gmall0715.bean.UserAddress;

import java.util.List;

public interface UserAddressService {

    /**
     * 根据用户id查询用户地址列表
     * @param userId
     * @return
     */
    List<UserAddress> findUserAddressByUserId(String userId);

}
