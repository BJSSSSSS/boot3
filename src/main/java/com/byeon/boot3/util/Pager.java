package com.byeon.boot3.util;

import lombok.Data;

@Data
public class Pager {

	//DB에서 몇개씩 조회(파라미터의 값 가능)
	private Integer perPage;
	
	//DB에서 조회할 시작 인덱스 번호
	private Integer startRow;
	
	//페이지 번호(파라미터의 값)
	private Integer pn;
	
	//검색어(파라미터의 값)
	private String search;
	
	//검색 종류(파라미터의 값)
	private String kind;
	
	//JSP 사용 변수
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
	
	
	
	//lombok이 있어도 직접 만들어줌 - 기본값을 부여하기 위해서
	public Integer getPerPage() {
		if(this.perPage == null || this.perPage < 1) {
			this.perPage = 8;
		}
		return this.perPage;
	}
	
	public Integer getPn() {
		if(this.pn == null || this.pn < 1) {
			this.pn = 1;
		}
		return this.pn;
	}
	
	//startRow 만들어주는 식
	public void makeRow() {
		//pn : 1, perPage : 10, startRow : 0
		//pn : 2, perPage : 10, startRow : 10
		//pn : 3, perPage : 10, startRow : 20
		this.startRow = (this.getPn()-1)*this.getPerPage(); 
	}
	

	//GETTER : get+멤버변수명, 멤버변수명의 첫 글자는 대문자
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return search;
	}
	
	//startNum, lastNum 만들어주는 식
	public void makeNum(Long totalCount) {
		//전체 row 갯수는 위 매개변수
		
		//전체 page 갯수
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
		
		//블럭당 갯수
		Long perBlock = 5L;
		
		//전체 Block의 갯수
		Long totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			totalBlock++;
		}
		
		//page번호로 현재 몇번쨰 Block인지 계산
		Long curBlock = this.getPn()/perBlock;
		if(this.getPn()%perBlock != 0) {
			curBlock++;
		}
		
		//curBlock으로 startNum, lastNum 구하기
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		//이전, 다음 블럭 유무
		//this.pre = false; 안써도 되는듯?
		if(curBlock > 1) {
			this.pre = true;
		}
		
		//this.next = false;
		if(totalBlock > curBlock) {
			this.next = true;
		}
		
		//현재 블럭이 마지막 블럭번호와 같다면
		if(curBlock == totalBlock) {
			this.lastNum = totalPage;
		}
		
		if(totalCount == 0) {
			this.lastNum = 0L;
		}

		
	}
	
	
}





