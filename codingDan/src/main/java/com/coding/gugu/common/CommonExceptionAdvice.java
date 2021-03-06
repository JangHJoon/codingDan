package com.coding.gugu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice
{
	private static final Logger log = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView commonError(Exception e)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/error/error_common");
		mav.addObject("exception", e);
		
		return mav;
	}
}
