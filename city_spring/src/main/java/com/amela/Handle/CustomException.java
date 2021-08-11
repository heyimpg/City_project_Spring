package com.amela.Handle;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomException {

    //Handle chung cho việc chưa login
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionNotFoundPage()
    {
        //Bắt tạm lỗi session với Exception
        ModelAndView modelAndView = new ModelAndView("error404");
        return modelAndView;
    }

}
