package com.coding.gugu.common;

public class CommonAbstructVO
{
	// for pagination
	private int page = 1;
	private long totalCount = 0;
	private int rowPerPage = 10;
	
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page =  page < 0 ? 1 : page;
	}
	public long getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}
	
	public void setRowPerPage(int rowPerPage)
	{
		if(rowPerPage <= 0 || rowPerPage >= 100)
		{
			return;
		}
		
		this.rowPerPage = rowPerPage;
	}
	
	// for myBatis
	public int getRowStart()
	{
		return (this.page -1) * this.rowPerPage;
	}
	// for myBatis
	public int getRowEnd()
	{
		return this.page * this.rowPerPage;
	}
	
	
}
