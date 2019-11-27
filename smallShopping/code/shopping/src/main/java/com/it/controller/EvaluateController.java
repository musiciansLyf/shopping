package com.it.controller;

import com.it.entity.Evaluate;
import com.it.service.EvaluateService;
import com.it.service.OrderService;
import com.it.service.UserService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈〉<br>
 *
 * @author
 * @create 2019/1/16 16:00
 * @since 1.0.0
 */
@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private OrderService orderService;


    /**
     *
     *
     * @return
     *//*
    @ResponseBody
    @GetMapping("evaluate.do")
    public TableResultResponse enlistmentTables() {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Evaluate> evaluateList = evaluateService.getCategoryByUserId(itdragonUtils.getSessionUser().getId());
        for (Evaluate record : evaluateList) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("consignee", record.getConsignee());
            resultMap.put("iphone", record.getIphone());
            resultMap.put("evaluate", record.getProvince() + record.getCity() + record.getCounty() + record.getParticular());
            if ("1".equals(record.getStatus())) {
                resultMap.put("default", "yes");
            }
            infoList.add(resultMap);
        }
        return Result.tableResule(0, infoList);
    }
*/

    /**
     * 新增
     */
    @ResponseBody
    @PostMapping("/evaluate.do")
    public ResultResponse add(String productId, String orderId, String content) {
        Evaluate evaluate = new Evaluate();
        evaluate.setUserId(itdragonUtils.getSessionUser().getId());
        evaluate.setContent(content);
        evaluate.setProductId(productId);
        evaluate.setTime(DateUtil.getNowDateSS());
        boolean result = evaluateService.insertEvaluate(evaluate);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        //评价成功后将待评价的商品订单删除
        orderService.deleteByPrimaryKey(orderId);
        return Result.resuleSuccess();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     *//*
    @ResponseBody
    @DeleteMapping("/evaluate.do")
    public ResultResponse delEn(String id) {
        boolean result = evaluateService.deleteevaluateByPrimaryKey(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }*/
    /*
     *//**
     * 编辑地址弹窗
     *
     * @param mv
     * @return
     *//*
    @RequestMapping("/editIframe.do")
    public ModelAndView editIframe(ModelAndView mv, String id) {
        evaluate evaluate = evaluateService.selecevaluate(id);
        mv.addObject("evaluate", evaluate);
        mv.setViewName("/front/editFrame");
        return mv;
    }*/

    /* *//**
     * 修改
     *
     * @param evaluate
     * @return
     *//*
    @ResponseBody
    @PutMapping("/evaluate.do")
    public ResultResponse edit(evaluate evaluate) {
        boolean result = evaluateService.updateevaluateByPrimaryKey(evaluate);
        if (!result) {
            return Result.resuleError("修改失败,未知错误");
        }
        return Result.resuleSuccess();
    }*/
}