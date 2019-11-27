package com.it.util;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author
 * @create 2018/10/6 18:30
 * @since 1.0.0
 */
public class Result {
    /**
     * 空参
     * @return
     */
    public static ResultResponse resuleSuccess(){
        ResultResponse response =new ResultResponse();
        response.setCode(0);
        return  response;
    }

    /**
     * 全参
     * @param o
     * @param msg
     * @return
     */
    public static ResultResponse resuleSuccess(Object o,String msg){
        ResultResponse response =new ResultResponse();
        response.setCode(0);
        response.setObje(o);
        response.setMsg(msg);
        return  response;
    }

    /**
     * 一参
     * @param o
     * @return
     */
    public static ResultResponse resuleSuccess(Object o){
        ResultResponse response =new ResultResponse();
        response.setCode(0);
        response.setObje(o);
        return  response;
    }

    public static ResultResponse resuleError(String msg){
        ResultResponse response =new ResultResponse();
        response.setMsg(msg);
        return  response;
    }

    /**
     * 表格返回封装对象
     * @param count 总数
     * @param list  表格数据
     * @return TableResultResponse对象
     */
    public static TableResultResponse tableResule(long count, List<Map<String, Object>> list) {
        TableResultResponse response = new TableResultResponse();
        response.setCount(count);
        response.setData(list);
        return response;
    }
}