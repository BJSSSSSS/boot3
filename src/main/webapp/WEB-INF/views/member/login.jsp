<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
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
	<div class="container mt-4">
	<div class="row mt-4">
		<div class="alert alert-light" role="alert">
	  		<h4 style="text-transform: uppercase;">${board} Login</h4>
		</div>
	</div>
		
		<div class="row mt-4">
		
			<!-- HTML Form tag 대신  Spring Form tag 사용 -->
			<form:form modelAttribute="memberVO" method="post">
			<!-- 컨트롤러에서 넘어온 속성명 맨 앞글자 소문자를 받아서 사용 -->
				<div class="row mb-3">
			    	<label for="id" class="col-sm-2 col-form-label">ID</label>
			    	<div class="col-sm-10">
			      		<!-- <input type="text" class="form-control" id="id" name="id"> -->
			      		<form:input path="id" cssClass="form-control" id="id"/><!-- name 대신 path -->
			      		<div>
			      			<form:errors path="id"></form:errors>
			      		</div>
			    	</div>
			  	</div>
			  	<div class="row mb-3">
			    	<label for="pw" class="col-sm-2 col-form-label">PW</label>
			    	<div class="col-sm-10">
			      		<!-- <input type="password" class="form-control" id="pw" name="pw"> -->
			      		<form:password path="pw" cssClass="form-control" id="pw"/><!-- name 대신 path -->
			      		<div>
			      			<form:errors path="pw" cssStyle="color:red;"></form:errors>
			      		</div>
			    	</div>
			  	</div>
			
				<button type="submit" class="btn btn-primary">Login</button>
				
				<div class="row">
					<button type="button" id="find"class="btn btn-danger">ID찾기</button>
				</div>
			</form:form>
			
		</div>
	</div>
	
	
<c:import url="../temp/header_script.jsp"></c:import>
<script type="text/javascript">
	$("#find").click(function(){
		location.href="./findId";
	})
</script>
</body>
</html>