package com.coding.gugu.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.gugu.board.dao.BoardDAO;
import com.coding.gugu.board.domain.BoardData;
import com.coding.gugu.board.domain.BoardParam;

@Service
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
		return dao.read(bno);
	}
	
	public void modify(BoardParam vo) throws Exception
	{ 
		dao.update(vo);
	}
	
	public void remove(Integer bno) throws Exception
	{
		dao.delete(bno);
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
	
}
