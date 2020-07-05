package com.coding.gugu.message.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.gugu.board.domain.BoardParam;
import com.coding.gugu.board.service.BoardService;
import com.coding.gugu.common.pagination.util.ComPageUtils;
import com.coding.gugu.message.domain.MessageVO;
import com.coding.gugu.message.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController
{
	private static final Logger log = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService service;
	
	@PostMapping("/")
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo) throws Exception
	{
		ResponseEntity<String> entity = null;
		
		try
		{
			service.addMessage(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch(Exception e)
		{	
			log.error(e.getMessage());
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
