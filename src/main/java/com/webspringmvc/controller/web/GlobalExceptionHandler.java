package com.webspringmvc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, Model model, HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		if (requestURL.contains("admin")) {
			model.addAttribute("userType", "admin");
		}
        return new ModelAndView("redirect:/notification/404");
    }
}
