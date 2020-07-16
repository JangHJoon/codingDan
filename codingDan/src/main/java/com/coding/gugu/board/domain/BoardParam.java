package com.coding.gugu.board.domain;

import org.apache.ibatis.type.Alias;

import com.coding.gugu.common.pagination.model.ComPageData;

import lombok.Getter;
import lombok.Setter;

@Alias("boardParam")
@Getter @Setter
public class BoardParam extends ComPageData
{
	private Integer bno;
	private String title;
	private String content;
	private String writer;

	private String searchType;
	private String searchKeyword;
	
	private String[] files;
	private String fileName;
}
