package com.coding.gugu;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import oracle.jdbc.driver.OracleDriver;

public class DBConnectionTest 
{
	@SuppressWarnings("unused")
	private static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	private static final String ORACLE_USER = "c##hjoon";
	private static final String ORACLE_PASSWORD = "Dhfkzmf";
	
	@Test
	public void testConnection() throws Exception
	{
		
//		Class.forName(DRIVER_CLASS);
		DriverManager.registerDriver(new OracleDriver());
		
		try( Connection con = DriverManager.getConnection(JDBC_URL,ORACLE_USER,ORACLE_PASSWORD))
		{
			System.out.println(con);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
