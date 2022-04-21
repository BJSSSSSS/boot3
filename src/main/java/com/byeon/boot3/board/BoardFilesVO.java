package com.byeon.boot3.board;

import com.byeon.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;


//fileDown을 사용하기 위해 공통적인것을 묶은 FileVO를 상속받고
//추가로 필요한 변수를 선언해서 각각 사용한다
//이때 lombok(@Data)가 FileVO에도 사용되어서 중복선언으로 에러가 뜬다
//그래서 이때는 @EqualsAndHashCode(callSuper = false)를 추가로 사용해준다
@Data
@EqualsAndHashCode(callSuper = false)
public class BoardFilesVO extends FileVO{

	private Long num;
	
}
