package com.it.service;

import com.it.entity.Address;

import java.util.List;

public interface AddressService {


    Address selecAddress(String id);

    /**
     * 新增
     *
     * @param Address
     * @return
     */
    boolean insertAddress(Address Address);

    /**
     * 修改
     *
     * @param Address
     * @return
     */
    boolean updateAddressByPrimaryKey(Address Address);

    /**
     * 删除父分了
     *
     * @param ids
     * @return
     */
    boolean deleteAddressByPrimaryKey(String ids);

    /**
     * @param userId
     * @return
     */
    List<Address> getCategoryByUserId(String userId);

    /**
     * 获取当前用户的默认地址
     * @param userId
     * @return
     */
    Address getMOren(String userId);

}
