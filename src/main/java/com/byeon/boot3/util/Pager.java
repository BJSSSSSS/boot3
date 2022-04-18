package com.byeon.boot3.util;

import lombok.Data;

@Data
public class Pager {

	//DB에서 몇개씩 조회
	private Integer perPage;
	
	//DB에서 조회할 시작 인덱스 번호
	private Integer startRow;
	
	//페이지 번호(파라미터의 값)
	private Integer pn;
	
	//lombok이 있어도 직접 만들어줌 - 기본값을 부여하기 위해서
	public Integer getPerPage() {
		if(this.perPage == null || this.perPage < 1) {
			this.perPage = 10;
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
	
	//검색 사용 변수
	private String search;
	private String kind;
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return search;
	}
	
	
}
