package com.it.controller;

import com.it.entity.Address;
import com.it.service.AddressService;
import com.it.service.UserService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈〉<br>
 *
 * @author
 * @create 2019/1/16 16:00
 * @since 1.0.0
 */
@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    /**
     * 地址管理列表加载
     *
     * @return
     */
    @ResponseBody
    @GetMapping("address.do")
    public TableResultResponse enlistmentTables() {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Address> addressList = addressService.getCategoryByUserId(itdragonUtils.getSessionUser().getId());
        for (Address record : addressList) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("consignee", record.getConsignee());
            resultMap.put("iphone", record.getIphone());
            resultMap.put("address", record.getProvince() + record.getCity() + record.getCounty() + record.getParticular());
            if ("1".equals(record.getStatus())) {
                resultMap.put("default", "yes");
            }
            infoList.add(resultMap);
        }
        return Result.tableResule(0, infoList);
    }

    /**
     * 新增
     *
     * @param address
     * @return
     */
    @ResponseBody
    @PostMapping("/address.do")
    public ResultResponse add(Address address) {
        if (address.getStatus() == null) {
            address.setStatus("0");
        }
        boolean result = addressService.insertAddress(address);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/address.do")
    public ResultResponse delEn(String id) {
        boolean result = addressService.deleteAddressByPrimaryKey(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 编辑地址弹窗
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editIframe.do")
    public ModelAndView editIframe(ModelAndView mv, String id) {
        Address address = addressService.selecAddress(id);
        mv.addObject("address", address);
        mv.setViewName("/front/editFrame");
        return mv;
    }

    /**
     * 修改
     *
     * @param address
     * @return
     */
    @ResponseBody
    @PutMapping("/address.do")
    public ResultResponse edit(Address address) {
        boolean result = addressService.updateAddressByPrimaryKey(address);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        return Result.resuleSuccess();
    }
}