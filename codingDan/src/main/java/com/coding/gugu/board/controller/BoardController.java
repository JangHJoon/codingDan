package com.coding.gugu.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.gugu.board.domain.BoardParam;
import com.coding.gugu.board.service.BoardService;
import com.coding.gugu.common.pagination.util.ComPageUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController
{
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/register")
	public void resigterGet(BoardParam vo, Model model) throws Exception
	{
		log.info("register get...");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardParam vo, RedirectAttributes rtr) throws Exception
	{
		log.info("register post....");
		
		service.regist(vo);
		
		rtr.addFlashAttribute("mng", "success");
		
		return "redirect:/board/listPage";
	}
	
	@GetMapping("/listPage")
	public String listPage(HttpServletRequest request, BoardParam vo, Model model) throws Exception
	{
		ComPageUtils.pagingSetting(request, vo);
		
		model.addAttribute("list", service.listPage(vo));
		model.addAttribute("vo", vo);
		
		return "board/list";
	}
	
	@GetMapping("/read")
	public void read(BoardParam vo, Model model) throws Exception
	{
		model.addAttribute("detail", service.read(vo.getBno()));
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/remove")
	public String remove(BoardParam vo, RedirectAttributes rtr) throws Exception
	{
		service.remove(vo.getBno());
		
		rtr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listPage?" + vo.getQueryString();
	}
	
	@GetMapping("/modify")
	public void modifyGet(BoardParam vo, Model model) throws Exception
	{
		model.addAttribute("detail", service.read(vo.getBno()));
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardParam vo, RedirectAttributes rtr) throws Exception
	{
		service.modify(vo);
		
		rtr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/read?" + vo.getQueryString() + "&bno=" + vo.getBno();
	}


}
