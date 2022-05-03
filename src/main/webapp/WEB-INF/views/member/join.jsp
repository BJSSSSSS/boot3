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
	  		<h4 style="text-transform: uppercase;">${board} Join</h4>
		</div>
	</div>
	
	
	<div class="row mt-4">
		<%-- <form action="./join" method="post" enctype="multipart/form-data"> --%>
		<form:form modelAttribute="memberVO" method="post" enctype="multipart/form-data">
		  <div class="row mb-3">
		    <label for="id" class="col-sm-2 col-form-label">ID</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" class="form-control" id="id" name="id"> -->
		      <form:input path="id" cssClass="form-control" id="id"/>
		      <div>
			      <form:errors path="id" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="pw" class="col-sm-2 col-form-label">PW</label>
		    <div class="col-sm-10">
		      <!-- <input type="password" class="form-control" id="pw" name="pw"> -->
		      <form:password path="pw" cssClass="form-control" id="pw"/>
    		  <div>
			      <form:errors path="pw" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="pwC" class="col-sm-2 col-form-label">PWCHECK</label>
		    <div class="col-sm-10">
		      <!-- <input type="password" class="form-control" id="pwC" name="pwC"> -->
		      <form:password path="pwC" cssClass="form-control" id="pwC"/>
		      <div>
			      <form:errors path="pwC" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div> 
		  <div class="row mb-3">
		     <label for="name" class="col-sm-2 col-form-label">NAME</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" class="form-control" id="name" name="name"> -->
		      <form:input path="name" cssClass="form-control" id="name"/>
		      <div>
			      <form:errors path="name" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		     <label for="email" class="col-sm-2 col-form-label">EMAIL</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" class="form-control" id="email" name="email"> -->
		      <form:input path="email" cssClass="form-control" id="email"/>
		      <div>
			      <form:errors path="email" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		     <label for="phone" class="col-sm-2 col-form-label">PHONE</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" class="form-control" id="phone" name="phone"> -->
		      <form:input path="phone" cssClass="form-control" id="phone"/>
		      <div>
			      <form:errors path="phone" cssStyle="color:red;"></form:errors>
			  </div>
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="photo" class="col-sm-2 col-form-label">File</label>
		    <div class="col-sm-10">
		      <input type="file" name="photo" class="form-control"> 
		    </div>
		  </div>
		  
		  
		  <button type="submit" class="btn btn-primary">Join</button>
		
		</form:form>
		<%-- </form> --%>
	
	</div>
	
	<div class="row">
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="" id="all">
		  <!-- 여기서 label을 사용하는 이유는 checkbox를 클릭 안하고 label에 있는 이름을 클릭해도 체크가 가능 -->
		  <!-- 이때 label을 사용하려면 label에 있는 for와 input checkbox에 있는 id가 동일해야 사용 가능하다 -->
		  <label class="form-check-label" for="all">
		    checkbox - All
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input ch" type="checkbox" value="" id="check1">
		  <label class="form-check-label" for="check1">
		    checkbox2
		  </label>
		</div>
		
		<div class="form-check">
		  <input class="form-check-input ch" type="checkbox" value="" id="check2">
		  <label class="form-check-label" for="check2">
		    checkbox3
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input ch" type="checkbox" value="" id="check3">
		  <label class="form-check-label" for="check3">
		    checkbox4
		  </label>
		</div>
		
	</div>

</div>	

<c:import url="../temp/header_script.jsp"></c:import>

<script type="text/javascript">
	
	//전체동의 체크
	$("#all").click(function(){
		
		$(".ch").prop("checked", $("#all").prop("checked"));
		
		/* if($(this).prop("checked")){
			$(".ch").prop("checked", true);	
		} */

	});
	

	//각 체크 확인
	$(".ch").on("click", function(){
		
		let check = true;
		
		//$("#all").prop("checked", true);
		
		//for 문
		$(".ch").each(function(idx, item){
			if(!$(item).prop("checked")){
				check = false;
			}
		});
		
		$("#all").prop("checked", check);
		
	});



</script>



</body>
</html>