package com.coding.gugu.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.board.domain.ReplyData;
import com.coding.gugu.board.domain.ReplyParam;

@Repository
public class ReplyDAO
{
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.coding.gugu.ReplyMapper";
	
	public long listCnt(ReplyParam vo)
	{
		return this.session.selectOne(namespace + ".listCnt", vo);
	}
	
	public List<ReplyData> listPage(ReplyParam vo) throws Exception
	{
		return this.session.selectList(namespace + ".listPage", vo);
	}
	
	public int create(ReplyParam vo) throws Exception
	{
		return this.session.insert(namespace+".create", vo);
	}

	public void update(ReplyParam vo) throws Exception
	{
		this.session.update(namespace + ".update",vo );
	}

	public void delete(ReplyParam vo) throws Exception
	{
		this.session.delete(namespace + ".delete",vo);
		
	}

}
