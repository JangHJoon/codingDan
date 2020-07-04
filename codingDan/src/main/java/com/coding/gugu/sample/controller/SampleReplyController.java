package com.coding.gugu.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleReplyController
{
	
	@GetMapping("/test")
	public String ajaxTest()
	{
		return "sample/ajaxTest";
	}

}
