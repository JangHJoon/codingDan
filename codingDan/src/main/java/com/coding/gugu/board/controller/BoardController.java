package com.coding.gugu.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.gugu.board.domain.BoardVO;
import com.coding.gugu.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController
{
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/register")
	public void resigterGet(BoardVO vo, Model model) throws Exception
	{
		log.info("register get...");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rtr) throws Exception
	{
		log.info("register post....");
		
		service.regist(vo);
		
		rtr.addFlashAttribute("mng", "success");
		
		return "redirect:listAll";
	}
	
	@GetMapping("/listAll")
	public void listAll(Model model) throws Exception
	{
		log.info("show all list..... ");
		
		model.addAttribute("list", service.listAll());
	}
	
	@GetMapping("/listPage")
	public String listPage(BoardVO vo, Model model) throws Exception
	{
		log.info("show all list..... ");
		
		model.addAttribute("list", service.listPage(vo));
		model.addAttribute("vo", vo);
		
		return "board/list";
	}
	
	@GetMapping("/read")
	public void read(@RequestParam("bno") int bno, Model model) throws Exception
	{
		model.addAttribute("vo", service.read(bno));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rtr) throws Exception
	{
		service.remove(bno);
		
		rtr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}
	
	@GetMapping("/modify")
	public void modifyGet(@RequestParam("bno") int bno, Model model) throws Exception
	{
		model.addAttribute("vo", service.read(bno));
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardVO vo, RedirectAttributes rtr) throws Exception
	{
		service.modify(vo);
		
		rtr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}


}
