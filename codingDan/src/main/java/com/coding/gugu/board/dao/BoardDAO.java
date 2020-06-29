package com.coding.gugu.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.board.domain.BoardVO;

@Repository
public class BoardDAO
{
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.coding.gugu.BoardMapper";
	
	public int create(BoardVO vo) throws Exception
	{
		return this.session.insert(namespace+".create", vo);
	}
	
	public BoardVO read(Integer bno) throws Exception
	{
		return this.session.selectOne(namespace+".read", bno);
	}
	
	public void update(BoardVO vo) throws Exception
	{
		this.session.update(namespace + ".update",vo );
		
	}
	
	public void delete(Integer bno) throws Exception
	{
		this.session.delete(namespace + ".delete",bno);
		
	}
	
	public List<BoardVO> listAll() throws Exception
	{
		return this.session.selectList(namespace + ".listAll");
	}
	
	public List<BoardVO> listPage(BoardVO vo) throws Exception
	{
		return this.session.selectList(namespace + ".listPage", vo);
	}

	public long listCnt(BoardVO vo)
	{
		return this.session.selectOne(namespace + ".listCnt", vo);
	}
}
