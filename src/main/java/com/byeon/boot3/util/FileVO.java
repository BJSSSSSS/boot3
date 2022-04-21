package com.byeon.boot3.util;

import lombok.Data;
import lombok.EqualsAndHashCode;


//fileDown을 사용하기위해서 member, board 공통적으로 사용하기 위해 공통적인것만 묶이는 VO를 생성
//이후 각각 file을 담는 VO에 상속시켜준다!
//이때 lombok을 상속 받는 대상도 사용하면 오류가 떠서 상속받는 대상에
//@EqualsAndHashCode(callSuper = false)를 추가로 사용해준다
@Data
public class FileVO {
	
	private Long fileNum;
	private String fileName;
	private String oriName;

}
