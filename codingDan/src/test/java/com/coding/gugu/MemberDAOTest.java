package com.coding.gugu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding.gugu.sample.domain.MemberVO;
import com.coding.gugu.sample.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
			locations = { "file:src/main/webapp/WEB-INF/spring/**/root_context.xml" }
		)
public class MemberDAOTest
{
	@Autowired
	private MemberDAO memberDao;
	
	@Test
	public void testTime() throws Exception
	{
		System.out.println(memberDao.getTime());
	}
	
	@Test
	public void insertMemberTest() throws Exception
	{
		MemberVO vo = new MemberVO();
		vo.setUserid("user0");
		vo.setUserpw("user0");
		vo.setUsername("userZero");
		vo.setEmail("user0@google.com");
		
		memberDao.insertMember(vo);
	}
	
	@Test
	public void readMember() throws Exception
	{
		String userid = "user0";
		
		MemberVO member = memberDao.readMember(userid);
		
		System.out.println(member);
	}
	
	@Test
	public void readMemberWithPw() throws Exception
	{
		String userid = "user0";
		String userpw = "user0";
		
		MemberVO member = memberDao.readWithPw(userid, userpw);
		
		System.out.println(member);
	}
	
}
