package com.shangma.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)// 不是null的数据才会被携带到返回数据中
public class AxiosResult<T> {
    // 响应状态
    private int status;
    // 响应消息
    private String message;
    // 响应数据
    private T data;

    private AxiosResult(AxiosStatus axiosStatus,T t){
        this.status = axiosStatus.getStatus();
        this.message = axiosStatus.getMessage();
        this.data = t;
    }

    private static <T> AxiosResult<T> getInstance(AxiosStatus axiosStatus,T t){
        return new AxiosResult<T>(axiosStatus,t);
    }

    /**
     * 请求成功的方法
     */
    public static <T> AxiosResult<T> success(){
        return getInstance(AxiosStatus.OK,null);
    }

    /**
     * 请求成功的方法
     * 携带数据
     */
    public static <T> AxiosResult<T> success(T t){
        return getInstance(AxiosStatus.OK,t);
    }
    /**
     * 请求失败的方法
     */
    public static <T> AxiosResult<T> error(){
        return getInstance(AxiosStatus.ERROR,null);
    }
    /**
     * 请求失败的方法
     * 携带数据
     */
    public static <T> AxiosResult<T> error(T t){
        return getInstance(AxiosStatus.ERROR,t);
    }

    /**
     * 请求失败的方法
     * 自定义状态码
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus){
        return getInstance(axiosStatus,null);
    }

    /**
     * 请求失败的方法
     * 自定义状态码
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus,T t){
        return getInstance(axiosStatus,t);
    }
}
