package com.coding.gugu.board.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("replyData")
@Data
public class ReplyData
{
	private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;
	
	private Date regdate;
	private Date updatedate;
}
