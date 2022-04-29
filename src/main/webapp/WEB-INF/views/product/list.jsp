<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<style type="text/css">
	.detail{
		cursor: pointer;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<c:import url="../temp/header.jsp"></c:import>


<div class="container mt-4">
	<div class="row mt-4">
		<div class="alert alert-primary" role="alert">
	  		<h4 class="text-center" style="text-transform: uppercase;">${board} List</h4>
		</div>
	</div>
	
	<div class="row row-cols-2 row-cols-md-4 mb-4 text-center">
		<c:forEach items="${list}" var="vo" varStatus="i">
			<!-- 전체를 a태그로 감싸봄, card 자체를 클릭하면 넘어가게 만드려고 -->
			<%-- <a href="./detail?productNum=${vo.productNum}"><!-- a태그는 inline 그래서 다른거쓰자 --> --%>
			<div class="col detail" data-num="${vo.productNum}"><!-- location을 쓰려고 class, data-num 부여 -->
				<div class="card mb-4 rounded-3 shadow-sm" style="width: 18rem;">
					<img src="../resources/upload/product/${vo.filesVOs[0].fileName}" class="card-img-top" alt="...">
			  		<div class="card-body">
			    		<h6 class="card-title">챔피언 이름</h6>
			    		<p class="card-text">${vo.productName}</p>
			  		</div>
			  		<ul class="list-group list-group-flush">
					    <li class="list-group-item">챔피언 가격 : ${vo.productPrice}</li>
					</ul>
					<div class="card-body">
		    			<p class="card-text">${vo.productDetail}</p>
		  			</div>
				</div>
			</div>	
			<!-- </a> -->
		</c:forEach>
	</div>
	
	<div class="row justify-content-between">
		<div class="col-5">
			<form class="d-flex" action="./list" method="get">
				<div class="col-4 me-2">
				<select name="kind" class="form-select" aria-label=".form-select-sm example">
					<option value="col1">상품명</option>
					<option value="col2">상품내용</option>
				</select>
				</div>
				<div class="col-6 me-2"> 
		        	<input name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
		        </div>
		        <div class="col-2">
		        	<button class="btn btn-outline-success" type="submit">Search</button>
		        </div>
	        </form>
		</div>
		<div class="col-1">
			<a href="./add" type="button" class="btn btn-outline-primary">WRITE</a>
		</div>
	</div>
	
	<br><br>
	<div class="position-relative">
		<div class="position-absolute top-0 start-50 translate-middle">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			  
		    	<li class="page-item">
		    		<a class="page-link" aria-label="Previous" href="./list?pn=${pager.pre?pager.startNum-1:1}&kind=${pager.kind}&search=${pager.search}">
				 		<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			   
			    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			    	<li class="page-item"><a class="page-link" href="./list?pn=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			    </c:forEach>

		    	<li class="page-item">
		    		<a class="page-link" href="./list?pn=${pager.next?pager.lastNum+1:pager.lastNum}&kind=${pager.kind}&search=${pager.search}">
		    			<span aria-hidden="true">&raquo;</span>
		    		</a>
		    	</li>
			   
			  </ul>
			</nav>
		</div>
	</div>
	
	

</div>




<c:import url="../temp/header_script.jsp"></c:import>

<script type="text/javascript">
	$(".detail").click(function(){
		let num = $(this).attr("data-num");
		location.href="./detail?productNum="+num;
	})
</script>

</body>
</html>