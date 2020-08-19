package com.ffw.common.http;


/**
 * @Description web请求返回数据封装类
 * @Author fufengwen
 * @Time 2020/8/4 14:28
 */

public class RestResult<T>{
    private Integer code;
    private T data;
    private String message;
    public RestResult(T data){
        this.code = HttpReturnStatusCode.SUCCESS.getCode();
        this.message = HttpReturnStatusCode.SUCCESS.getDes();
    }

    public RestResult(Integer code , T data, String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static RestResult ok(String message){
        return new RestResult(HttpReturnStatusCode.SUCCESS.getCode(),null,message);
    }

    public RestResult data(T data){
        this.code = HttpReturnStatusCode.SUCCESS.getCode();
        this.message = HttpReturnStatusCode.SUCCESS.getDes();
        return this;
    }

    public static RestResult error(String message){
        return new RestResult(HttpReturnStatusCode.FAILED.getCode(),null,message);
    }
}

enum HttpReturnStatusCode{
    SUCCESS(200,"成功"),
    FAILED(404,"失败");

    private Integer code;
    private String des;
    private HttpReturnStatusCode(Integer code,String des){
        this.code = code;
        this.des = des;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getDes(){
        return this.des;
    }
    public static HttpReturnStatusCode getDesByCode(Integer code) {
        if (code == 0) {
            return null;
        } else {
            HttpReturnStatusCode[] values = values();
            int len = values.length;

            for(int i = 0; i < len; ++i) {
                HttpReturnStatusCode item = values[i];
                if (item.code.equals(code)) {
                    return item;
                }
            }
            return null;
        }
    }
}
