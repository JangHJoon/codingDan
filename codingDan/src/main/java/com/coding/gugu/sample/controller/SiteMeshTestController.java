package com.coding.gugu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteMeshTestController
{
	private static final Logger log = LoggerFactory.getLogger(SiteMeshTestController.class);
	
	@RequestMapping("sitemesh")
	public String sitemesh()
	{
		log.info("sitemesh test controller call....");
		
		return "sample/sample_sitemesh";
	}
	
}
