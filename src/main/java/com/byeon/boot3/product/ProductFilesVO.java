package com.byeon.boot3.product;

import com.byeon.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductFilesVO extends FileVO{

	private Long productNum;
	
}
