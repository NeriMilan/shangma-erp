package com.shangma.common.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Ryze
 * @create : 2021-11-23 23:00
 * @Description :
 */
@RestControllerAdvice
public class MySystemExceptionHandler {

    @ExceptionHandler(MySystemException.class)
    public AxiosResult catchHandle(MySystemException mySystemException){
        return AxiosResult.error(AxiosResult.error(AxiosStatus.ERROR_DATABASE_OPTIONAL));
    }

}
