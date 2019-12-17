package PetClinic.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController{
    @ExceptionHandler(Exception.class)
    public ModelAndView notFoundException(Exception exception)
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }
}
