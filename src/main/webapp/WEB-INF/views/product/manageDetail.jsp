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
<title>Insert title here</title>
</head>
<body>
<c:import url="../temp/header.jsp"></c:import>
<c:import url="./temp_detail.jsp"></c:import>

	
	<div class="container my-4">
		<div class="col-2 d-flex">
			<a href="./update?productNum=${vo.productNum}" role="button" class="btn btn-success mx-1">UPDATE</a>
			<!-- 삭제는 하지 않고(삭제하면 구매한 사람들 제품이 이상해지니까, 제품 판매중지를 하면 update로 만들어줄것 -->	
		</div>
	</div>
	
	
	
<c:import url="../temp/header_script.jsp"></c:import>	

</body>
</html>