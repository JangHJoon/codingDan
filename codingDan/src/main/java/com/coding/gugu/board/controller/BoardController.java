package com.coding.gugu.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.gugu.board.domain.BoardParam;
import com.coding.gugu.board.service.BoardService;
import com.coding.gugu.common.pagination.util.ComPageUtils;
import com.coding.gugu.common.utils.CommonUploadUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController
{
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	public static final String uploadPath = "C:\\upload";
	
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
	
	
	@ResponseBody
	@GetMapping("/{bno}/attach")
	public ResponseEntity<List<Map<String,Object>>> attachList(@PathVariable("bno") Integer bno) throws Exception
	{
		List<String> fileNames = service.selectAttach(bno);
		
		List<Map<String,Object>> body = new ArrayList<>();
		
		for(String fileName : fileNames)
		{
			Map<String, Object> sendMap = new HashMap<>();
			sendMap.put("fileName", fileName);
			
			File file = new File(uploadPath, fileName);
			Path path = file.toPath();
			String contentType = Files.probeContentType(path);
			boolean isImage = CommonUploadUtils.isImage(contentType);
			sendMap.put("isImage", isImage);
			
			body.add(sendMap);
		}
		
		return ResponseEntity.ok(body);
	}
	
	
	@ResponseBody
	@PostMapping(value="/uploadAjax", produces="text/plain;charset=UTF-8")
	public ResponseEntity<Map<String,Object>> uploadAjaxPost(MultipartFile file) throws Exception
	{
		log.info("orginalName : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("contentType : " + file.getContentType());
		
		Map<String, Object> sendMap = new HashMap<>();
		sendMap.put("fileName", CommonUploadUtils.uploadFile(uploadPath, file));
		sendMap.put("isImage", CommonUploadUtils.isImage(file.getContentType()));
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(sendMap);
	}
	
	@ResponseBody
	@GetMapping(value="/displayFile")
	public ResponseEntity<Resource> displayFile(String fileName) throws Exception
	{
		
		log.info("file : " + fileName);
		
		File file = new File(uploadPath, fileName);
		Path path = file.toPath();
		String contentType = Files.probeContentType(path);
		boolean isImage = CommonUploadUtils.isImage(contentType);
				
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		fileName = fileName.substring(fileName.indexOf("_") + 1);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(
								isImage ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM
						)
				.contentLength(file.length())
				.body(resource);
		
		//return new ResponseEntity<String>(CommonUploadUtils.uploadFile(uploadPath, fileName), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@GetMapping(value="/downFile")
	public ResponseEntity<Resource> downFile(String fileName) throws Exception
	{
		
		log.info("file : " + fileName);
		
		File file = new File(uploadPath, fileName);
		Path path = file.toPath();
		String contentType = Files.probeContentType(path);
		boolean isImage = CommonUploadUtils.isImage(contentType);
		if(isImage)
		{
			int split = fileName.indexOf("s_");
			fileName = fileName.substring(0,split) + fileName.substring(split + 2);
			file = new File(uploadPath, fileName);
		}
				
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		fileName = fileName.substring(fileName.indexOf("_") + 1);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(
								isImage ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM
						)
				.contentLength(file.length())
				.body(resource);
	}
	
	@ResponseBody
	@PostMapping(value="/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) throws Exception
	{
		log.info("delete target file : " + fileName);
		
		File file = new File(uploadPath, fileName);
		Path path = file.toPath();
		String contentType = Files.probeContentType(path);
		boolean isImage = CommonUploadUtils.isImage(contentType);
		if(isImage)
		{
			int split = fileName.indexOf("s_");
			String orgfileName = fileName.substring(0,split) + fileName.substring(split + 2);
			File orgFile = new File(uploadPath, orgfileName);
			orgFile.delete();
		}
		
		file.delete();

		return ResponseEntity.ok("deleted");
	}
	


}
