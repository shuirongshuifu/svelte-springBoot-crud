/*
BusinessException.java 是业务异常的封装类，用于统一返回业务异常数据。
就是一个手动抛出错误函数，就像throw new Error('用户名不能为空'); 

这里抛出去的错误，会被全局错误捕获类GlobalExceptionHandler捕获到
GlobalExceptionHandler有点像前端的全局错误捕获：
window.addEventListener('error', (event) => {
  console.error('全局错误：', event.error);
  // 发送错误报告到服务器
  reportError(event.error);
});
*/ 
package com.people_sys.people.config;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private int code; // 自定义错误码（如4001：用户不存在）


    public BusinessException(int code, String message) {
        super(message); // 异常信息
        this.code = code;
    }
}
