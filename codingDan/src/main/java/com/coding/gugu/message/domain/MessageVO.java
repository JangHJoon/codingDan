package com.coding.gugu.message.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("messageVO")
@Data
public class MessageVO
{
	private Integer mno;
	private String targetid;
	private String sendid;
	private String message;
	private Date opendate;
	private Date senddate;
}
