package com.coding.gugu.board;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding.gugu.board.dao.BoardDAO;
import com.coding.gugu.board.domain.BoardData;
import com.coding.gugu.board.domain.BoardParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/**/root_context.xml" }
	)
public class BoardDAOTest2
{

	@Autowired
	private BoardDAO dao;
	
	@Test
	public void listPageTest() throws Exception
	{
		int page = 3;
		
		BoardParam vo = new BoardParam();
		
		vo.setPage(page);
		
		List<BoardData> list = dao.listPage(vo);
		
		System.out.println("list.size() : " +  list.size());
		
		for(BoardData item : list)
		{
			System.out.println(item);
		}
	}
}
