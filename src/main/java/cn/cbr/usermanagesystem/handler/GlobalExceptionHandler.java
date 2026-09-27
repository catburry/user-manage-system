package cn.cbr.usermanagesystem.handler;

import cn.cbr.usermanagesystem.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleRuntimeException(RuntimeException e){
        // 第二个参数把异常对象传进去，才会打印完整堆栈
        log.error("业务异常", e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e){
        log.error("系统内部异常", e);
        return Result.error("服务器内部错误");
    }
}
