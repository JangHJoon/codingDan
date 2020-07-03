package com.coding.gugu.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.gugu.sample.domain.ProductVO;

@RestController
@RequestMapping("/sample")
public class SampleRestController2
{
	@RequestMapping("/send403")
	public ResponseEntity<Void> send403()
	{
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping("/send404")
	public ResponseEntity<ProductVO> send404()
	{
		return new ResponseEntity<>(new ProductVO("상태", 1000),HttpStatus.FORBIDDEN);
	}

}
