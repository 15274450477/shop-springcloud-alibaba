package org.example.utils.exception;

import org.example.utils.constants.HttpCode;
import org.example.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e){
        logger.error("服务器抛出了异常：{}",e);
        return new Result<String>(HttpCode.FAILUER, "执行失败", e.getMessage());
    }
}
