package com.coding.gugu.board;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding.gugu.board.dao.BoardDAO;
import com.coding.gugu.board.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/**/root_context.xml" }
	)
public class BoardDAOTest
{

	@Autowired
	private BoardDAO dao;
	
	private int bno;
	
	@Before
	public void insertTest() throws Exception
	{
		System.out.println(1);
		BoardVO vo = new BoardVO();
		vo.setTitle("새글");
		vo.setContent("새글입니다.");
		vo.setWriter("글쓴이");
		
		dao.create(vo);
		bno = vo.getBno();
	}
	
	@Test
	public void readAndUpdateTest() throws Exception
	{
		System.out.println(2);
		System.out.println(dao.read(bno).toString());
		
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		vo.setTitle("수정글");
		vo.setContent("수정글 입니다.");
		dao.update(vo);
	}
	
	@Test 
	public void readAllTest() throws Exception
	{
		List<BoardVO> list = dao.listAll();
		
		for(BoardVO vo : list)
		{
			System.out.println(vo);
		}
	}
	
	@After
	public void deleteTest() throws Exception
	{
		System.out.println(4);
		dao.delete(bno);
	}
}
