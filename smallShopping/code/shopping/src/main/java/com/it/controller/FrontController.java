package com.it.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.it.entity.*;
import com.it.service.*;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * controller
 *
 * @author
 * @create 2018/10/9 17:02
 * @since 1.0.0
 */
@RequestMapping("/front")
@Controller
public class FrontController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private CartService cartService;

    /**
     * 界面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        //按时间降序拿到商品信息
        List<Product> timeList = productService.getListRoule(4, "时间");
        //按销售量降序拿到商品信息
        List<Product> salesList = productService.getListRoule(4, "销量");
        mv.addObject("timeList", timeList);
        mv.addObject("salesList", salesList);
        mv.setViewName("/front/index");
        return mv;
    }

    /**
     * 商品列表页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/list.do")
    public ModelAndView list(ModelAndView mv, String ptCategoryId) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        //循环判断选择分类是否为这个如果是,选中标签
        for (PtCategory ptCategory : ptCategoryList) {
            if (ptCategory.getId().equals(ptCategoryId)) {
                ptCategory.setSelected("layui-this");
            }
        }
        mv.addObject("ptCategoryList", ptCategoryList);
        //拿到当前分类下的所有子分类
        List<Category> categoryList = categoryService.getCategoryByPtCategoryId(ptCategoryId);
        mv.addObject("categoryList", categoryList);
        mv.addObject("ptCategoryId", ptCategoryId);
        //获得所有商品的集合数
        mv.addObject("count", productService.zunNumber());
        mv.setViewName("/front/list");
        return mv;
    }

    /**
     * 商品列表页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/search.do")
    public ModelAndView search(ModelAndView mv, String paramSearch) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        //循环判断选择分类是否为这个如果是,选中标签
        mv.addObject("ptCategoryList", ptCategoryList);
        //获得所有商品的集合数
        mv.addObject("count", productService.zunNumber());
        mv.addObject("paramSearch", paramSearch);
        mv.setViewName("/front/search");
        return mv;
    }

    /**
     * 商品详情页
     *
     * @param mv
     * @return
     */
    @RequestMapping("/detail.do")
    public ModelAndView detail(ModelAndView mv, String productId) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        Product product = productService.selectByPrimaryKey(productId);
        mv.addObject("product", product);
        //拿到详情图片前三个
        String[] split = product.getDasImg().split(";");
        List<String> imgTitle = new ArrayList<>();
        int a = 0;
        if (imgTitle.size() < 3) {
            a = imgTitle.size();
        } else {
            a = 4;
        }
        for (int i = 1; i < a; i++) {
            String s = split[i];
            if (ItdragonUtils.stringIsNotBlack(s)) {
                imgTitle.add(s);
            }
        }
        //拿到所有详情图片
        String[] splitAll = product.getDasImg().split(";");
        List<String> allImg = new ArrayList<>();
        for (String s : splitAll) {
            if (ItdragonUtils.stringIsNotBlack(s)) {
                allImg.add(s);
            }
        }
        mv.addObject("imgTitle", imgTitle);
        mv.addObject("allImg", allImg);
        //拿到规格和参数的集合
        String[] splitSpecification = product.getSpecification().split(";");
        String[] splitParameter = product.getParameter().split(";");
        List<String> listSpecification = new ArrayList<>();
        List<String> listParameter = new ArrayList<>();
        for (String s : splitSpecification) {
            if (ItdragonUtils.stringIsNotBlack(s)) {
                listSpecification.add(s);
            }
        }
        for (String s : splitParameter) {
            if (ItdragonUtils.stringIsNotBlack(s)) {
                listParameter.add(s);
            }
        }
        mv.addObject("listSpecification", listSpecification);
        mv.addObject("listParameter", listParameter);
        //拿到该商品的评价信息
        List<Evaluate> evaluateList = evaluateService.getEvaluateByProductId(productId);
        //将评价用户信息放进去
        for (Evaluate evaluate : evaluateList) {
            evaluate.setUser(userService.selectByPrimaryKey(evaluate.getUserId()));
        }
        mv.addObject("evaluateList", evaluateList);
        mv.addObject("evaluateListSize", evaluateList.size());
        //按销售量降序拿到商品信息
        List<Product> salesList = productService.getListRoule(4, "销量");
        mv.addObject("salesList", salesList);
        mv.setViewName("/front/detail");
        return mv;
    }

    /**
     * 用户中心跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/user.do")
    public ModelAndView users(ModelAndView mv) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        mv.addObject("user", userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId()));
        //查询用户的各种订单数目
        Integer daiFa = 0;
        Integer daiShou = 0;
        Integer daiPin = 0;
        List<Order> orderList = orderService.getOrderListByUserId(itdragonUtils.getSessionUser().getId());
        for (Order order : orderList) {
            if ("待发货".equals(order.getStatus())) {
                daiFa = daiFa + 1;
            } else if ("待收货".equals(order.getStatus())) {
                daiShou = daiShou + 1;
            } else if ("已完成".equals(order.getStatus())) {
                daiPin = daiPin + 1;
            }
        }
        mv.addObject("daiFa", daiFa);
        mv.addObject("daiShou", daiShou);
        mv.addObject("daiPin", daiPin);
        mv.setViewName("/front/user");
        return mv;
    }

    /**
     * 地址管理跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/useradd.do")
    public ModelAndView useradd(ModelAndView mv) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        mv.setViewName("/front/useradd");
        return mv;
    }

    /**
     * 新增地址弹窗
     *
     * @param mv
     * @return
     */
    @RequestMapping("/iframe.do")
    public ModelAndView iframe(ModelAndView mv) {
        mv.setViewName("/front/iframe");
        return mv;
    }

    /**
     * 我的收藏跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/usercol.do")
    public ModelAndView usercol(ModelAndView mv) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        mv.setViewName("/front/usercol");
        return mv;
    }

    /**
     * 我的购物车跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/usershop.do")
    public ModelAndView usershop(ModelAndView mv) {
        //判断用户是否登录
        if (itdragonUtils.isGogin()) {
            mv.addObject("isGogin", "yes");
            mv.addObject("user", itdragonUtils.getSessionUser());
        } else {
            mv.addObject("isGogin", "no");
        }
        //拿到父分类标签集合
        List<PtCategory> ptCategoryList = categoryService.getPtCategory();
        mv.addObject("ptCategoryList", ptCategoryList);
        mv.setViewName("/front/usershop");
        return mv;
    }

    /**
     * 支付方法
     */
    @ResponseBody
    @PostMapping("/buy.do")
    public ResultResponse buyProduct(Float proPrice, String productId, String imgUrl, Integer number) {
        Order order = new Order();
        order.setNum(number);
        order.setStatus("待发货");
        order.setZumPrice(number * proPrice);
        order.setUserId(itdragonUtils.getSessionUser().getId());
        order.setProductId(productId);
        if (addressService.getMOren(itdragonUtils.getSessionUser().getId()) == null) {
            return Result.resuleError("未设置默认地址");
        }
        if (proPrice > userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId()).getBalance()) {
            return Result.resuleError("余额不足");
        }
        order.setAddressId(addressService.getMOren(itdragonUtils.getSessionUser().getId()).getId());
        order.setImgUrl(imgUrl);
        order.setOrderTime(DateUtil.getNowDateSS());
        order.setOrderUuid(itdragonUtils.getOrderIdByUUId());
        boolean result = orderService.insert(order);
        if (result) {
            //新增成功之后将该商品的销量加number
            Product product = productService.selectByPrimaryKey(productId);
            product.setSales(product.getSales() + number);
            productService.updateByPrimaryKey(product);
            //将用户的余额减少
            User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
            user.setBalance(user.getBalance() - proPrice);
            userService.updateByPrimaryKey(user);
            return Result.resuleSuccess();
        }
        return Result.resuleError("请求失败");
    }

    /**
     * 评价商品弹窗
     *
     * @param mv
     * @return
     */
    @RequestMapping("/evaluate.do")
    public ModelAndView evaluate(ModelAndView mv, String productId, String orderId) {
        mv.addObject("productId", productId);
        mv.addObject("orderId", orderId);
        mv.setViewName("/front/evaluate");
        return mv;
    }

    /**
     * 加入购物车方法
     */
    @ResponseBody
    @PostMapping("/inCart.do")
    public ResultResponse inCart(Float proPrice, String productId, String imgUrl, Integer number, Float price, String parameter, String specification) {
        Cart cart = new Cart();
        cart.setNum(number);
        cart.setPrice(price);
        cart.setProPrice(proPrice);
        cart.setParameter(parameter);
        cart.setSpecification(specification);
        cart.setProductName(productService.selectByPrimaryKey(productId).getName());
        cart.setUserId(itdragonUtils.getSessionUser().getId());
        cart.setProductId(productId);
        cart.setImgUrl(imgUrl);
        boolean result = cartService.insertCart(cart);
        if (result) {
            return Result.resuleSuccess();
        }
        return Result.resuleError("请求失败");
    }

    /**
     * 购物车结算方法
     */
    @ResponseBody
    @PostMapping("/settle.do")
    public ResultResponse settle(String param) {
        System.out.println(param);
        JSONArray parse = (JSONArray) JSONArray.parse(param);
        for (Object o : parse) {
            JSONObject json = (JSONObject) o;
            System.out.println(json);
            Order order = new Order();
            order.setNum(json.getInteger("number"));
            order.setStatus("待发货");
            order.setZumPrice(json.getInteger("number") * json.getFloat("proPrice"));
            order.setUserId(itdragonUtils.getSessionUser().getId());
            order.setProductId(json.getString("productId"));
            order.setAddressId(addressService.getMOren(itdragonUtils.getSessionUser().getId()).getId());
            order.setImgUrl(json.getString("avatar"));
            order.setOrderTime(DateUtil.getNowDateSS());
            order.setOrderUuid(itdragonUtils.getOrderIdByUUId());
            if (json.getInteger("number") * json.getFloat("proPrice") > userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId()).getBalance()) {
                return Result.resuleError("余额不足");
            }
            boolean result = orderService.insert(order);
            if (result) {
                //新增成功之后将该商品的销量加number
                Product product = productService.selectByPrimaryKey(json.getString("productId"));
                product.setSales(product.getSales() + json.getInteger("number"));
                productService.updateByPrimaryKey(product);
                //之后将购物车中此信息删除
                cartService.deleteCartByPrimaryKey(json.getString("id"));
                //将用户的余额减少
                User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
                user.setBalance(user.getBalance() - json.getInteger("number") * json.getFloat("proPrice"));
                userService.updateByPrimaryKey(user);
            }

        }
        return Result.resuleSuccess();
    }

    /**
     * 修改个人信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("/updatUser.do")
    public ModelAndView updatUser(ModelAndView mv) {
        User user = userService.selectByPrimaryKey(itdragonUtils.getSessionUser().getId());
        mv.addObject("user", user);
        mv.setViewName("/front/updateUser");
        return mv;
    }
}