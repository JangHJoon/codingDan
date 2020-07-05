package com.coding.gugu.message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coding.gugu.message.dao.MessageDAO;
import com.coding.gugu.message.dao.PointDAO;
import com.coding.gugu.message.domain.MessageVO;
import com.coding.gugu.message.domain.UserVO;

@Service
@Transactional
public class MessageService
{
	private static final Logger log = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private PointDAO pointDAO;
	
	public void addMessage(MessageVO vo) throws Exception
	{
		messageDAO.create(vo);
		
		UserVO pVO = new UserVO();
		pVO.setId(vo.getSendid());
		pVO.setPoint(10);
		pointDAO.updatePoint(pVO);
	}
	
	public MessageVO readMessage(MessageVO vo) throws Exception
	{
		Integer mno = vo.getMno();
		messageDAO.updateStat(mno);
		
		UserVO pVO = new UserVO();
		pVO.setId(vo.getTargetid());
		pVO.setPoint(5);
		pointDAO.updatePoint(pVO);
		
		return messageDAO.readMessage(mno);
	}
	
}
