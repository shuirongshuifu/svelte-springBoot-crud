/*
ApiResponse.java 是 API 响应的封装类，用于统一返回 API 响应数据。
如返回数据为：{ "code": 200, "message": "success", "data": ... }
*/ 
package com.people_sys.people.config;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int code;       // 状态码（如200成功，400参数错误，500系统错误）
    private String message; // 响应消息（成功时为"success"，失败时为错误信息）
    private T data;         // 业务数据（成功时返回）

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    // 错误响应
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
