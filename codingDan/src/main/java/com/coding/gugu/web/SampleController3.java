package com.coding.gugu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding.gugu.domain.ProductVO;

@Controller
public class SampleController3
{
	private static final Logger log = LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping("doD")
	public String doD(Model model)
	{
		log.info("doD called......");
		
		ProductVO p = new ProductVO("Sample Product", 10000);
		
		model.addAttribute("product", p);
		
		return "sampleD";
	}
	
}
