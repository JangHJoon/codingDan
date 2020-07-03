package com.coding.gugu.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.gugu.board.dao.ReplyDAO;
import com.coding.gugu.board.domain.ReplyData;
import com.coding.gugu.board.domain.ReplyParam;

@Service
public class ReplyService
{
	private static final Logger log = LoggerFactory.getLogger(ReplyService.class);
	
	@Autowired
	private ReplyDAO dao;
	
	public void addReply(ReplyParam vo) throws Exception
	{
		dao.create(vo);
	}
	
	public List<ReplyData> listPage(ReplyParam vo) throws Exception
	{
		return dao.listPage(vo);
	}
	
	public void modifyReply(ReplyParam vo) throws Exception
	{ 
		dao.update(vo);
	}
	
	public void removeReply(ReplyParam vo) throws Exception
	{
		dao.delete(vo);
	}

	
}
