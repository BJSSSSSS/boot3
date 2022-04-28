package com.byeon.boot3.util;

import com.byeon.boot3.member.MemberFilesVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPager extends Pager{

	private String id;
	
}
