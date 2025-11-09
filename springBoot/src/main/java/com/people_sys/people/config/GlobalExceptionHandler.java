/*
GlobalExceptionHandler.java 是全局异常处理类，用于统一处理异常。

就像一个"全局的try-catch"网，能自动捕获Controller异常
*/ 
package com.people_sys.people.config;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理业务异常（自定义异常）
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusinessException(BusinessException ex) {
        // 返回自定义错误码和异常信息
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

    // 处理系统异常（如NullPointerException、IOException等）
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleSystemException(Exception ex) {
        // 打印异常堆栈（便于后端调试），返回友好提示
        ex.printStackTrace(); // 实际开发中建议用日志框架（如Logback）
        return ApiResponse.error(500, "系统异常：" + ex.getMessage()); // 显示具体异常信息
    }

    // 处理参数校验异常（如@NotNull、@Size注解校验失败）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidException(MethodArgumentNotValidException ex) {
        // 获取校验失败的具体字段和信息
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.error(400, "参数错误：" + msg);
    }
}
