package com.coding.gugu.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.board.domain.BoardData;
import com.coding.gugu.board.domain.BoardParam;

@Repository
public class BoardDAO
{
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.coding.gugu.BoardMapper";
	
	public int create(BoardParam vo) throws Exception
	{
		return this.session.insert(namespace+".create", vo);
	}
	
	public BoardData read(Integer bno) throws Exception
	{
		return this.session.selectOne(namespace+".read", bno);
	}
	
	public void update(BoardParam vo) throws Exception
	{
		this.session.update(namespace + ".update",vo );
		
	}
	
	public void delete(Integer bno) throws Exception
	{
		this.session.delete(namespace + ".delete",bno);
		
	}
	
	public List<BoardData> listAll() throws Exception
	{
		return this.session.selectList(namespace + ".listAll");
	}
	
	public List<BoardData> listPage(BoardParam vo) throws Exception
	{
		return this.session.selectList(namespace + ".listPage", vo);
	}

	public long listCnt(BoardParam vo)
	{
		return this.session.selectOne(namespace + ".listCnt", vo);
	}
}
