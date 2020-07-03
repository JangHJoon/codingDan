package com.coding.gugu.board.domain;

import org.apache.ibatis.type.Alias;

import com.coding.gugu.common.pagination.model.ComPageData;

import lombok.Getter;
import lombok.Setter;

@Alias("replyParam")
@Getter @Setter
public class ReplyParam extends ComPageData
{
	private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;
}
