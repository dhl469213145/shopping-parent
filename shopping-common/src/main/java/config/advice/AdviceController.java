package config.advice;

import api.R;
import error.RRException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice  // 支持json格式
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception e) {
        if(e instanceof RRException) {
            return R.fail("请稍后再试:" + e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            return R.fail("校验错误：" + me.getBindingResult().getFieldError().getDefaultMessage());
        }
        return R.fail("error." + e.getMessage());
    }
}
