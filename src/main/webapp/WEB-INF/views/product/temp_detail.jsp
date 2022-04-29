<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 
	<div class="container">
		<h1>Detail Page</h1>
		
		<div class="row"><!-- requestScope.vo.filesVOs -->
			<c:forEach items="${vo.filesVOs}" var="fileVO">
				<!-- <h1>${pageScope.files.name}</h1>  여기서 pageScope와 requestScope는 생략가능
				-> var에 만들어진 영역은 pageScope, 찾는 순서는 생명주기가 짧은순서! pageScope -> requestScope-->
				<!-- 하지만 헷갈릴수 있어서 문제도 생길수 있어서 items에 넣는 변수명과 var은 조금 다르게 해주는게 좋다 -->
				<img alt="" src="../resources/upload/product/${fileVO.fileName}">
			</c:forEach>
		</div>
		
		
		<div class="row">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">${vo.productName}</li>
					<li class="list-group-item">${vo.id}</li>
				</ul>
			
				<div class="card-body">
					${vo.productDetail}
				</div>
				
			</div>
			
		</div>

	</div>
	
	
	