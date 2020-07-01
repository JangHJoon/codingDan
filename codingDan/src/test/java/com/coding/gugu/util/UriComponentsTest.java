package com.coding.gugu.util;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentsTest
{

	@Test
	public void utilsTest()
	{
		UriComponents uc = UriComponentsBuilder
				.fromPath("/board/listPage")
				.queryParam("page","4")
				.queryParam("testParam","1111")
				.build();
		
		System.out.println(uc.toString());
		
		System.out.println(UriComponentsBuilder
				.fromUriString("www.org.com/pagd/dkjd?cks=11&dkbkd=100")
				.build()
				.getQuery());
	}
}
