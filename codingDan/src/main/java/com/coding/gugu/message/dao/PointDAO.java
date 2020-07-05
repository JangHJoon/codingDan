package com.coding.gugu.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.gugu.message.domain.UserVO;

@Repository
public class PointDAO
{
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.coding.gugu.PointMapper";
	
	public void updatePoint(UserVO vo) throws Exception
	{
		session.update(namespace + ".updatePoint", vo);
	}
}
