package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.it.entity.Address;
import com.it.mapper.AddressMapper;
import com.it.service.AddressService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2019/2/15 17:53
 * @since 1.0.0
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper AddressMapper;
    @Resource
    private ItdragonUtils itdragonUtils;

    @Override
    public Address selecAddress(String id) {
        return AddressMapper.selectById(id);
    }

    @Override
    public boolean insertAddress(Address Address) {
        Address.setUserId(itdragonUtils.getSessionUser().getId());
        Integer insert = AddressMapper.insert(Address);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAddressByPrimaryKey(Address Address) {
        Integer update = AddressMapper.updateById(Address);
        if (update > 0) {
            if ("1".equals(Address.getStatus())) {
                EntityWrapper<Address> wrapper = new EntityWrapper<>();
                wrapper.eq("status", "1");
                wrapper.ne("id", Address.getId());
                com.it.entity.Address address = new Address();
                address.setStatus("0");
                AddressMapper.update(address, wrapper);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAddressByPrimaryKey(String ids) {
        Integer delete = AddressMapper.deleteById(ids);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Address> getCategoryByUserId(String userId) {
        EntityWrapper<Address> searchInfo = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(userId)) {
            searchInfo.eq("userId", userId);
        }
        return AddressMapper.selectList(searchInfo);
    }

    @Override
    public Address getMOren(String userId) {
        Address address = new Address();
        address.setStatus("1");
        address.setUserId(userId);
        Address address1 = AddressMapper.selectOne(address);
        return address1;
    }
}