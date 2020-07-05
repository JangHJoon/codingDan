package com.coding.gugu.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.message.domain.MessageVO;

@Repository
public class MessageDAO
{
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.coding.gugu.MessageMapper";
	
	public void create(MessageVO vo) throws Exception
	{
		session.insert(namespace + ".create", vo);
	}
	
	public MessageVO readMessage(Integer mno) throws Exception
	{
		return session.selectOne(namespace + ".readMessage", mno);
	}
	
	public void updateStat(Integer mno) throws Exception
	{
		session.update(namespace + ".updateStat", mno);
	}
	
	public void test()
	{
	}
	
}
