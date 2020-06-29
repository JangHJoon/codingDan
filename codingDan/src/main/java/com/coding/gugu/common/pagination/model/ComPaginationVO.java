package com.coding.gugu.common.pagination.model;

import java.util.StringJoiner;

public class ComPaginationVO
{
	private String link;
	private String queryString;
	private int page;
	private int rowPerPage; 
	private int pagePerView;
	private long totalCount;
	
	public ComPaginationVO()
	{
		this.page = 1 ;
		this.rowPerPage = 10 ;
		this.pagePerView = 5 ;
		this.totalCount = 0 ;
	}
	
	// for jsp
	public boolean isEnablePrev()
	{
		return this.getStartPage() - 1 > 0 ;
	}
	// for jsp
	public boolean isEnableNext()
	{
		int nextPage = (((int)(this.page-1)/this.pagePerView) + 1)*this.pagePerView + 1;
		int endPage = ((int)(this.totalCount-1)/this.rowPerPage)*this.pagePerView + 1;
		return endPage >= nextPage;
	}
	// for jsp
	public int getStartPage()
	{
		return ((int)(this.page-1)/this.pagePerView)*this.pagePerView + 1;
	}
	// for jsp
	public int getEndPage()
	{
		int nextPage = (((int)(this.page-1)/this.pagePerView) + 1)*this.pagePerView + 1;
		int endPage = ((int)(this.totalCount-1)/this.rowPerPage)*this.pagePerView + 1;
		return endPage > nextPage ? nextPage - 1 : endPage;
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

	public void setPage(int page)
	{
		if(page <= 0)
		{
			this.page = 1;
			return;
		}
		
		this.page = page;
		
	}

	public void setRowPerPage(int rowPerPage)
	{
		if(rowPerPage <= 0 || rowPerPage > 100)
		{
			this.rowPerPage = 10;
			return;
		}
		
		this.rowPerPage = rowPerPage;
	}
	
	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}

	public long getTotalCount()
	{
		return totalCount;
	}

	public int getPage()
	{
		return page;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getQueryString()
	{
		StringJoiner sj = new StringJoiner("&");
		
		String[] arrQry = queryString.split("&");
		for(int i=0 ; i<arrQry.length ; i++)
		{
			if(arrQry[i].indexOf("page=") < 0)
			{
				sj.add(arrQry[i]);
			}
		}
		
		sj.add("page=");
		
		return sj.toString();
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	public int getPagePerView()
	{
		return pagePerView;
	}

	public void setPagePerView(int pagePerView)
	{
		this.pagePerView = pagePerView;
	}

	public int getRowPerPage()
	{
		return rowPerPage;
	}
	
	@Override
	public String toString()
	{
		return "ComPaginationVO [link=" + link + ", page=" + page + ", rowPerPage=" + rowPerPage + ", pagePerView="
				+ pagePerView + ", totalCount=" + totalCount + "]";
	}
	
}
