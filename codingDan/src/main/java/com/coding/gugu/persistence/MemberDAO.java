package com.coding.gugu.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.domain.MemberVO;

@Repository
public class MemberDAO
{
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "com.coding.gugu.MemberMapper";
	
	public String getTime()
	{
		return this.sqlSession.selectOne(namespace + ".getTime");
	}
	
	public void insertMember(MemberVO vo)
	{
		this.sqlSession.insert(namespace + ".insertMember", vo);
	}
}
