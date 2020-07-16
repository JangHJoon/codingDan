package com.coding.gugu.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coding.gugu.board.controller.BoardController;
import com.coding.gugu.board.dao.BoardDAO;
import com.coding.gugu.board.domain.BoardData;
import com.coding.gugu.board.domain.BoardParam;
import com.coding.gugu.common.utils.CommonUploadUtils;

@Service
@Transactional
public class BoardService
{
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardDAO dao;
	
	public void regist(BoardParam vo) throws Exception
	{
		dao.create(vo);
	}
	
	public BoardData read(Integer bno) throws Exception
	{
		dao.updateCnt(bno);
		return dao.read(bno);
	}
	
	public void modify(BoardParam vo) throws Exception
	{ 
		Integer bno = vo.getBno();
		List<String> fileList = dao.selectAttach(bno);

		dao.update(vo);
		dao.deleteAllAttach(bno);

		String[] fileNames = vo.getFiles();
		
		for(String fileName : fileNames)
		{
			BoardParam newParam = new BoardParam();
			newParam.setFileName(fileName);
			newParam.setBno(bno);
			
			dao.insertAttach(newParam);
			
			if(fileList.contains(fileName))
			{
				fileList.remove(fileName);
			}
		}
		
		for(String fileName : fileList)
		{
			CommonUploadUtils.deleteFile(BoardController.uploadPath, fileName);
		}
	}
	
	public void remove(Integer bno) throws Exception
	{
		List<String> fileList = dao.selectAttach(bno);
		
		dao.delete(bno);
		
		for(String fileName : fileList)
		{
			CommonUploadUtils.deleteFile(BoardController.uploadPath, fileName);
		}
	}
	
	public List<BoardData> listAll() throws Exception
	{
		return dao.listAll();
	}
	
	public List<BoardData> listPage(BoardParam vo) throws Exception
	{
		vo.setTotalCount(dao.listCnt(vo));
		return dao.listPage(vo);
	}
	
	public List<String> selectAttach(Integer bno) throws Exception
	{
		return dao.selectAttach(bno);
	}
	
	public void deleteAllAttach(Integer bno) throws Exception
	{
		dao.deleteAllAttach(bno);
	}
	
	public void addAttach(BoardParam vo) throws Exception
	{
		dao.insertAttach(vo);
	}
	
}
