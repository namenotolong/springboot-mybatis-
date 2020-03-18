package com.huyong.advice;

import com.huyong.common.Result;
import com.huyong.exception.AuthException;
import com.huyong.exception.CommonException;
import com.huyong.exception.DAOException;
import com.huyong.exception.ParamBlankException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller统一异常处理
 * @author : huyong
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Result<?> handle(CommonException e){
        logger.error(ExceptionUtils.getStackTrace(e));
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ParamBlankException.class)
    @ResponseBody
    public Result<?> handle(ParamBlankException e){
        logger.error(ExceptionUtils.getStackTrace(e));
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(DAOException.class)
    @ResponseBody
    public Result<?> handle(DAOException e){
        logger.error(ExceptionUtils.getStackTrace(e));
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Result<?> handle(AuthException e){
        logger.error(ExceptionUtils.getStackTrace(e));
        return Result.error(e.getMessage(), 333);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> handle(Exception e){
        logger.error(ExceptionUtils.getStackTrace(e));
        return Result.error(ExceptionUtils.getStackTrace(e));
    }

}