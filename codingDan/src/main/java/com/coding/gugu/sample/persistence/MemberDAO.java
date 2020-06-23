package com.coding.gugu.sample.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.sample.domain.MemberVO;

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
	
	public MemberVO readMember(String userid) throws Exception
	{
		return this.sqlSession.selectOne(namespace + ".readMember", userid);
	}
	
	public MemberVO readWithPw(String userid, String userpw) throws Exception
	{
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		
		return this.sqlSession.selectOne(namespace+".readWithPw", vo);
	}
	
}
