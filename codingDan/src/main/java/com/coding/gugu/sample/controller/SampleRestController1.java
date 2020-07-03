package com.coding.gugu.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.gugu.sample.domain.ProductVO;

@RestController
@RequestMapping("/sample")
public class SampleRestController1
{

	@RequestMapping("/hello")
	public String hello()
	{
		return "Hello World!!";
	}
	
	@RequestMapping("/sendVO")
	public ProductVO sendVO()
	{
		return new ProductVO("사과",100);
	}
	
	@RequestMapping("/sendList")
	public List<ProductVO> sendList()
	{
		List<ProductVO> list = new ArrayList<>();
		
		list.add(new ProductVO("사과",100));
		list.add(new ProductVO("사과",101));
		list.add(new ProductVO("사과",102));
		list.add(new ProductVO("사과",103));
		
		return list;
	}
	
	@RequestMapping("/sendArray")
	public ProductVO[] sendArray()
	{
		ProductVO[] arr = new ProductVO[4];
		
		arr[0] = new ProductVO("사과",100);
		arr[1] = new ProductVO("사과",101);
		arr[2] = new ProductVO("사과",102);
		arr[3] = new ProductVO("사과",103);
		
		return arr;
	}
	
	@RequestMapping("/sendMap")
	public Map<String,ProductVO> sendMap()
	{
		Map<String, ProductVO> map = new HashMap<>();
		
		map.put("I0", new ProductVO("사과",100));
		map.put("I1", new ProductVO("사과",101));
		map.put("I2", new ProductVO("사과",102));
		map.put("I3", new ProductVO("사과",103));
		
		return map;
	}
}
