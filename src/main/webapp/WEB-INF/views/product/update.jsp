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
	
	<!-- 맨 위로 올림 -->
	<!-- 위지위그 사용하기위해 먼저 선언 -->
	<c:import url="../temp/header_script.jsp"></c:import>
  
	<!-- include summernote css/js -->
	<!-- 위지위그 사용하기 위해 갖고옴 -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
  
  
	<title>Insert title here</title>
</head>
<body>
<c:import url="../temp/header.jsp"></c:import>

<div class="container mt-4">
	<div class="row mt-4">
		<div class="alert alert-light" role="alert">
	  		<h4 style="text-transform: uppercase;">${board} Update</h4>
		</div>
	</div>
	
	
	<div class="row mt-4">
		<form action="./update" method="post" enctype="multipart/form-data">
		  <input type="hidden" name="productNum" value="${vo.productNum}" id="productNum">
		  <div class="row mb-3">
		    <label for="productName" class="col-sm-2 col-form-label">ProductName</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productName" name="productName" value="${vo.productName}">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productPrice" class="col-sm-2 col-form-label">ProductPrice</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productPrice" name="productPrice" value="${vo.productPrice}">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productCount" class="col-sm-2 col-form-label">ProductCount</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productCount" name="productCount" value="${vo.productCount}">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productDetail" class="col-sm-2 col-form-label">ProductDetail</label>
		    <div class="col-sm-10">		<!-- 나중에 받아올때 통일감을 주기 위해서 summernote.js contents로 통일해놓음, 다시 바꿔놓음 ㅅㅂ 가서 조정-->
		      <textarea class="form-control" id="productDetail" name="productDetail"></textarea>
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		  		<%-- <input type="hidden" id="sale" value="${vo.sale}"> --%>
		  	<div class="form-check"><!-- ajax라서 작동안했던거임.. -->
			  <input class="form-check-input sale" type="radio" value="1" ${vo.sale eq 1?"checked":''}  name="sale" id="flexRadioDefault1">
			  <label class="form-check-label" for="flexRadioDefault1">
			   	판매
			  </label>
			</div>
			
			<div class="form-check"><!-- ${vo.sale eq 1?checked:unchecked}, radio는 둘중 하나만 작동이라 된다!!! 참내 "" 안붙여서 안됨-->															 
			  <input class="form-check-input sale" type="radio" value="0" ${vo.sale eq 0?"checked":''} name="sale" id="flexRadioDefault2">
			  <label class="form-check-label" for="flexRadioDefault2">
			    판매중지
			  </label>
			</div>
		  	
		  </div>
		  
		  
		  <button id="fileAdd" type="button" class="btn btn-danger d-block my-4">FileAdd</button>
		  
		  <div>
			<c:forEach items="${vo.filesVOs}" var="fileVO">
				<h4>${fileVO.oriName}<button class="del" type="button" data-num="${fileVO.fileNum}">DELETE</button></h4>
			</c:forEach>
		  </div>
		  
		  
		  
		  <div id="fileResult"></div>

		  <button id="ajaxAdd" type="submit" class="btn btn-primary my-4">Update</button>
		</form>
	
	</div>
	
	
</div>	


<!-- 두가지 경로로 js import 해봄! -->
<!-- 기존 resources에서 받아오는 방식 -->
<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
<!-- static에서 가지고 오려면 나중에 뿌려질때 없어지므로 static은 빼고 루트로가서 바로 js -->
<script type="text/javascript" src="../js/summernote.js"></script>
<script type="text/javascript">

	summernoteInit("productDetail", "${vo.productDetail}");//매개변수 2개넣는거고, 여기서 EL문으로 받아와서 summernote.js에서 받아주는 방식
	fileAddInit(${vo.filesVOs.size()}); //size함수 호출로 count 값 가져온다 내가 했던것과 비교
	fileDeleteInit();
	
</script>

</body>
</html>