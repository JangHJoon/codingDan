package com.coding.gugu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController1
{
	private static final Logger log = LoggerFactory.getLogger(SampleController1.class);
	
	@RequestMapping("doA")
	public void doA()
	{
		log.info("doA called......");
	}
	
	@RequestMapping("doB")
	public void doB()
	{
		log.info("doB called......");
	}
	
}
