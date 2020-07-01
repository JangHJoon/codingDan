package com.coding.gugu.common.pagination.util;

import javax.servlet.http.HttpServletRequest;

import com.coding.gugu.common.pagination.model.ComPageData;

public class ComPageUtils
{

	public static void pagingSetting(HttpServletRequest request, ComPageData page)
	{
		page.setQueryString(request.getQueryString());
		page.setLink(request.getRequestURI());
	}
}
