<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
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
<!-- header -->
<c:import url="./temp/header.jsp"></c:import>

<div class="container mt-4">	
  		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="https://fncent.com/files/2022/02/23/875ea9925cc4988746519d241fd2b78a180649.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="https://fncent.com/files/2022/02/23/95c2c0687206848f8f867241a1115b1a180649.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="https://fncent.com/files/2022/02/23/c1ef220da543b3d053290efcb3dd2581180649.jpg" class="d-block w-100" alt="...">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
  	</div>
  	
  	<div class="container">
  		<c:if test="${not empty member}">
	  		<c:forEach items="${member.roleVOs}" var="member">
	  			<h6>${member.roleName}</h6>
	  		</c:forEach>
  		</c:if>
  	</div>
  	
<!-- ?????? -->
<!-- <div class="container">
	<input type="text" id="d1">
	<button id="btn">CLICK</button>
	<button id="btn2">CLICK2</button>
	<input type="checkbox" name="ch" class="ch" value="1">
	<input type="checkbox" name="ch" class="ch" value="2">
	<input type="checkbox" name="ch" class="ch" value="3">
	<input type="checkbox" name="ch" class="ch" value="4">
	<button id="btn3">CLICK3</button>
	<div id="result">
		
	</div>
</div> -->

<div class="container">
	<input type="text" id="v1">
	
	<input type="checkbox" class="num" name="num" value="a">
	<input type="checkbox" class="num" name="num" value="b">
	<input type="checkbox" class="num" name="num" value="c">
	<input type="checkbox" class="num" name="num" value="d">
	
	<button id="btn1">GET</button>
	<button id="btn2">POST</button>
    <button id="btn3">AJAX</button>
</div>


<div class="container">
	<h1>Spring Message</h1>
	<h1><spring:message code="hello" var="m"></spring:message></h1>
	<h1><spring:message code="test" text="???????????????"></spring:message></h1>
	<h1>${m}</h1> <!-- pageScope.m -->
	<h2>${m}</h2>
	<h1><spring:message code="board.title"></spring:message></h1>
	
</div>

<c:if test="${not empty member}">
	<!-- ?????????ID ??? ???????????????.-->
	<div class="container">
		<h1><spring:message code="member.login.info" arguments="${member.id}" text="???????????????"></spring:message> </h1>
	</div>
</c:if>



<c:import url="./temp/header_script.jsp"></c:import>

<script type="text/javascript">


	$("#btn1").on("click", function(){
		let v = $("#v1").val();
		console.log(v);
		$.get("./getTest?msg="+v, function(data){
			console.log("?????? ??????");
			console.log(data.trim());
		});
	});

	
	//btn2??? click?????? v1??? ????????? ?????? /postTest ????????? ???????????? ??????
	//???????????? getResult.jsp ????????????
	
	$("#btn2").on("click", function(){
		//console.log($("#v1").val());
		$.post("./postTest", {msg:$("#v1").val()}, function(data){
			console.log(data.trim());
		})
	});
	
	
	$("#btn3").click(function() {
		
		let ar = [1, 2, 3];
		let nums = [];
		
		$(".num").each(function(idx,item){
			if($(item).prop("checked")){
				nums.push($(item).val());
			}
		});
	
		let v = $("#v1").val();
		$.ajax({
			type: "POST",
			url: "./arrayTest",
			traditional: true,
			data: {
				msg: v,
				numbers: ar,
				nums: nums
			},
			success:function(d){
				console.log(d.trim());
			},
			error:function(){
				alert("?????? ??????");
			}
		});
	});
	
	



	//?????????
	//?????? ?????????????????????
	/* 
	const btn = document.getElementById("btn3");
	btn.addEventListener("click", function(){
		
	}); 
	
	const ch = document.getElementByClassName("ch"); // ??????
	
	ch.addEventListener(x)
	
	for(c of ch){
		c.addEventListener("click", function(){
			alert(this.value);
		});
	}
	
	*/
	//
	
	//jquery??? ???????????? ???????????? ???????????? ??????!
	/* $(".ch").click(function(){
		console.log(this.value);
	}); */
	
	//????????? ????????? ???????????? {} ??????
/* 	$(".ch").on({
		click:function(){
			console.log("click");
		},
		
		change:function(){
			console.log("change");
		}

	}); */
	
/* 	
	$("#btn").on("click", function(){
		console.log($("#d1").val());
	});
	
	
	
	$(".ch").click(function(e){
		//this??? ??????????????? ???????????? ?????????
		//console.log(this);
		//console.log($(this));
		//console.log(e.target);
		//??? ????????? ??????
		
		//let v = $(this).val()
		//console.log(v);
		
		let v = $(this).prop("checked");
		console.log(v);
		$(".ch").prop("checked", true);
		
	});
	
	
	$(".ch").change(function(){
		console.log("change Test");
	});
	
	
	$('#btn2').click(function(){

		$(".ch").each(function(idx, item){
			console.log("Index : ", idx);
			console.log("Item : ", item);
			console.log("Value : ", $(item).val());
		});
		
	});


	$('#btn').on("click", function(){
		alert("jquery");
	});
	
	$("#btn3").click(function(){
		
		//div??? ???????????? ?????????
		let r = "<div>";
		r = r + '<input type="checkbox" name="ch" class="ch" value="1">';
		r = r + "</div>";
		
		$("#result").append(r);

							//???????????? ?????? "" ??? ??????????????? ??? ???????????? ''??? ???????????????!
		//$("#result").append('<input type="checkbox" name="ch" class="ch" value="1">');
		
	});
	 */
	
	


</script>


</body>
</html>