package com.coding.gugu.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class CommonUploadUtils
{
	private static final Logger log = LoggerFactory.getLogger(CommonUploadUtils.class);

	public static String uploadFile(String rootPath, MultipartFile multipartFile) throws Exception
	{
		String result = null;
		
		String originalName = multipartFile.getOriginalFilename();
		
		String savedName = UUID.randomUUID().toString() + "_" + originalName;
		String savedPath = getDatePath();
		
		File target = new File(rootPath + savedPath, savedName);
		File dir = target.getParentFile();
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		
		multipartFile.transferTo(target);
		
		if(isImage(multipartFile.getContentType()))
		{
			result = makeThumbnail(rootPath, savedPath, savedName);
		}
		else
		{
			result = target.getAbsolutePath().substring(rootPath.length()).replace(File.separator, "/");
		}
		
		return result;
	}


	private static String getDatePath()
	{
		Calendar cal = Calendar.getInstance();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(File.separator);
		sb.append(cal.get(Calendar.YEAR));
		sb.append(File.separator);
		sb.append(new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1));
		sb.append(File.separator);
		sb.append(new DecimalFormat("00").format(cal.get(cal.get(Calendar.DATE))));
		
		return sb.toString();
	}
	
	private static String makeThumbnail(String rootPath, String path, String fileName) throws Exception 
	{
		BufferedImage sourceImg = ImageIO.read(new File(rootPath + path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailPath = rootPath + path + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailPath);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailPath.substring(rootPath.length()).replace(File.separator, "/");
	}
	
	public static boolean isImage(String contentType)
	{
		return Optional.ofNullable(contentType).orElse("").startsWith("image/");
	}
}
