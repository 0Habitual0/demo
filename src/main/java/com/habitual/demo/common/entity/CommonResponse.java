package com.habitual.demo.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 返回体包装类
 */
@Getter
@Setter
@ToString
public class CommonResponse implements Serializable {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String ERROR = "error";
    public static final String OK = "ok";

    /**
     * 状态
     */
    private String status;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    // 默认构造函数
    public CommonResponse() {
    }

    // 带参数的构造函数
    public CommonResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 静态成功方法
    public static CommonResponse success(Object data) {
        return new CommonResponse(OK, "操作成功", data);
    }

    // 静态失败方法
    public static CommonResponse fail(String message) {
        return new CommonResponse(ERROR, message, null);
    }

}
