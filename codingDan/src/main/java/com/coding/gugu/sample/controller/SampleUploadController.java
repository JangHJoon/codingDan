package com.coding.gugu.sample.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coding.gugu.common.utils.CommonUploadUtils;

@Controller
@RequestMapping("/sample")
public class SampleUploadController
{
	private static final Logger log = LoggerFactory.getLogger(SampleUploadController.class);
	
	private static final String uploadPath = "C:\\upload";
	
	@GetMapping("/uploadForm")
	public String uploadFormGet()
	{
		return "sample/uploadForm";
	}
	
	@PostMapping("/uploadForm")
	public String uploadFormPost(MultipartFile file, Model model) throws Exception
	{
		log.info("orginalName : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("contentType : " + file.getContentType());
		
		String savedName = this.uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName", savedName);
		
		return "sample/uploadResult";
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception
	{
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		
		File target = new File(uploadPath, savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}
	
	@GetMapping("/uploadAjax")
	public String uploadAjaxGet()
	{
		return "sample/uploadAjax";
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
