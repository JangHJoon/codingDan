package com.coding.gugu.board.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.coding.gugu.common.CommonAbstructVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("boardVO")
public class BoardVO extends CommonAbstructVO
{
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
}
