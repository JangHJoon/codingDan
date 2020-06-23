package com.coding.gugu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coding.gugu.domain.ProductVO;

@Controller
public class SampleController5
{
	private static final Logger log = LoggerFactory.getLogger(SampleController5.class);
	
	@RequestMapping("doJson")
	public @ResponseBody ProductVO doJson()
	{
		log.info("doJson called......");
		
		ProductVO vo = new ProductVO("샘플상품", 30000);
		
		return vo;
	}
	
}
