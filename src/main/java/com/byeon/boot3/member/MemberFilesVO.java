package com.byeon.boot3.member;

import com.byeon.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberFilesVO extends FileVO{
	
	private String id;
	

}
