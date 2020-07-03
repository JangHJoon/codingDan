package com.coding.gugu.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.gugu.board.domain.ReplyData;
import com.coding.gugu.board.domain.ReplyParam;
import com.coding.gugu.board.service.ReplyService;

@RestController
@RequestMapping("/board/{bno}/replies")
public class ReplyController
{
	private static final Logger log = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService service;
	
	@PostMapping("")
	public ResponseEntity<String> resigter(@RequestBody ReplyParam vo) throws Exception
	{
		ResponseEntity<String> entity = null;
		
		try
		{
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e)
		{
			log.error(e.getMessage());
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<List<ReplyData>> list(
			@PathVariable("bno") Integer bno,
			@PathVariable("page") Integer page
			) throws Exception
	{
		ResponseEntity<List<ReplyData>> entity = null;
		
		try
		{
			ReplyParam param = new ReplyParam();
			param.setBno(bno);
			param.setPage(page);
			param.setRowPerPage(5);
			entity = new ResponseEntity<List<ReplyData>>(service.listPage(param), HttpStatus.OK);
		} catch (Exception e)
		{
			log.error(e.getMessage());
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{rno}", method= {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> update(
			@PathVariable("rno") Integer rno,
			@RequestBody ReplyParam vo
			) throws Exception
	{
		ResponseEntity<String> entity = null;
		
		try
		{
			vo.setRno(rno);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e)
		{
			log.error(e.getMessage());
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{rno}", method= {RequestMethod.DELETE})
	public ResponseEntity<String> delete(
			@PathVariable("bno") Integer bno,
			@PathVariable("rno") Integer rno
			) throws Exception
	{
		ResponseEntity<String> entity = null;
		
		try
		{
			ReplyParam param = new ReplyParam();
			param.setBno(bno);
			param.setRno(rno);
			
			service.removeReply(param);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e)
		{
			log.error(e.getMessage());
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
