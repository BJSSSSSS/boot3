<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<table class="table table-dark table-striped">
		<thead>
			<tr>
				<th>Num</th>
				<th>Name</th>
				<th>Price</th>
				<th>Count</th>
				<th>판매여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.productNum}</td>
					<td class="detail" data-num="${vo.productNum}">${vo.productName}</td>
					<td>${vo.productPrice}</td>
					<td>${vo.productCount}</td>
					<!-- 3항연산자에 한글 가능 앞뒤로'' -->
					<td>${vo.sale eq 1?'판매중':'판매중지'}</td>
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<div class="row my-4 position-relative">
		<div class="position-absolute top-0 start-50 translate-middle">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			  
		    	<li class="page-item">
		    		<a class="page-link pager" aria-label="Previous" data-pn="${pager.pre?pager.startNum-1:0}" href="#">
				 		<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			   
			    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			    	<li class="page-item"><a class="page-link pager" data-pn="${i}" href="#">${i}</a></li>
			    </c:forEach>

		    	<li class="page-item">
		    		<a class="page-link pager" href="#" data-pn="${pager.next?pager.lastNum+1:0}">
		    			<span aria-hidden="true">&raquo;</span>
		    		</a>
		    	</li>
			   
			  </ul>
			</nav>
		</div>
	</div>
	