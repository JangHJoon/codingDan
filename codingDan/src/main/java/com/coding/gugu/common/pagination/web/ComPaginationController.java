package com.coding.gugu.common.pagination.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coding.gugu.common.pagination.model.ComPaginationVO;

@Controller
@RequestMapping("/common/pagination/")
public class ComPaginationController
{
	private static final Logger log = LoggerFactory.getLogger(ComPaginationController.class);
	
	@GetMapping("/includePagination")
	public ModelAndView includePagination(HttpServletRequest request, ComPaginationVO param)
	{
		ModelAndView mav = new ModelAndView("common/pagination/page_seg");

		param.setQueryString(request.getQueryString());
		
		mav.addObject("page", param);
		
		return mav;
	}
}
