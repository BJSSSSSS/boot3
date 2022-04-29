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
	  		<h4 style="text-transform: uppercase;">${board} Add</h4>
		</div>
	</div>
	
	<div class="row" id="list">
		<!-- 상품 리스트 ajax, name, price, count-->
		
	</div>
	
	
	
	<div class="row mt-4">
		<form action="./add" method="post" enctype="multipart/form-data">
		
		  <div class="row mb-3">
		    <label for="productName" class="col-sm-2 col-form-label">ProductName</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productName" name="productName">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productPrice" class="col-sm-2 col-form-label">ProductPrice</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productPrice" name="productPrice">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productCount" class="col-sm-2 col-form-label">ProductCount</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="productCount" name="productCount">
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		    <label for="productDetail" class="col-sm-2 col-form-label">ProductDetail</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" id="productDetail" name="productDetail"></textarea>
		    </div>
		  </div>
		  
		  <div class="row mb-3">
		  	
		  	<div class="form-check"><!-- ajax라서 작동안했던거임.. -->
			  <input class="form-check-input sale" type="radio" value="1" name="sale" id="flexRadioDefault1">
			  <label class="form-check-label" for="flexRadioDefault1">
			   	판매
			  </label>
			</div>
			
			<div class="form-check">															   <!-- checked 넣기 -->
			  <input class="form-check-input sale" type="radio" value="0" name="sale" id="flexRadioDefault2" checked>
			  <label class="form-check-label" for="flexRadioDefault2">
			    판매중지
			  </label>
			</div>
		  	
		  </div>
		  
		  
		  <button id="fileAdd" type="button" class="btn btn-danger d-block my-4">FileAdd</button>
		  
		  <div id="fileResult"></div>

		  <button id="ajaxAdd" type="button" class="btn btn-primary my-4">Add</button>
		</form>
	
	</div>
	
	
</div>	



<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
<script type="text/javascript" src="../js/summernote.js"></script>
<script type="text/javascript">

	summernoteInit("productDetail", "");
	
	
	
	
	
	//add.jsp안에 paging 처리
	//전역변수로 먼저 선언해서 pn 오류 안생기게 만들기
	let pn = 1;
	
	$("#list").on("click", ".pager", function(){
		//console.log($(this).attr("data-pn"));
		let checkPn = $(this).attr("data-pn");
		if(checkPn > 0){
			pn = checkPn
			getList();	
			//getList(checkPn);			
		}else{
			//이전 블럭과 다음 블럭이 없다
			alert('마지막 페이지 입니다');
		}

	});
	
	

	//list ajax url: ajaxList, Get
	//매개변수로 쓸땐 먼저 1 선언해줘야함 pn 값 미리 넣어놓기
	getList();//1);

	function getList(){//pn){
		$.ajax({
			type: "GET",
			url: "./ajaxList",
			data:{
				pn:pn,
				perPage:5
			},
			success:function(data){
				$("#list").html(data.trim());
			}
		
		});
	}

	
	//ajax 파일업로드 강사용
	$("#ajaxAdd").on("click", function(){
		
		let formData = new FormData();
		
		let productName = $("#productName").val();
		let productPrice = $("#productPrice").val();
		let productCount = $("#productCount").val();
		let productDetail = $("#productDetail").summernote("code");
		let sale = 0;
		$(".sale").each(function(idx, item){
			if($(item).prop("checked")){
				sale = $(item).val();
			}
		})
		
		
		$(".files").each(function(idx, item) {
			if(item.files.length > 0){				//length가 있는것들, file이 들어온것들 선택
				console.log(idx);					//index 번호
				console.log(item);					//<input type="file">
				console.log(item.files);			//input 태그의 file List
				console.log(item.files[0]);			//file list중 첫번째 파일
				console.log(item.files.length);		//file list중 첫번째 파일의 길이
				console.log(item.files[0].name);	//file list중 첫번째 파일의 이름
				//formData.append("파라미터명", 값);
				formData.append("files", item.files[0]);
			}
		});//each 끝
		
		formData.append('productName',  productName);
		formData.append('productPrice', productPrice);
		formData.append('productCount', productCount);
		formData.append('productDetail', productDetail);
		formData.append('sale', sale);
		
		$.ajax({
			type: "POST",
			url: "./add",
		    processData: false,
		    contentType: false,
			data:formData,
				
				/* {
				productName: $("#productName").val(),
				productPrice: $("#productPrice").val(),
				productCount: $("#productCount").val(),
				productDetail: $("#productDetail").summernote("code") // $("#productDetail").val()
													//뒤에 주석처리된것과 같은건데 summernote의 value를 가지고 올수 있는 코드
			},  */ 
			 
			
			success:function(data){
				if(data.trim() == '1'){
					alert("상품 등록 완료");
					//다시 호출해서 가지고오고
					//들어있는 값들 초기화
					getList();
					$("#productName").val("");
					$("#productPrice").val("");
					$("#productCount").val("");
					$("#productDetail").summernote("code", "");
					$("#fileResult").empty();
				}else{
					alert("상품 등록 실패");
				}
			},
			error:function(){
				alert("에러 발생");
			}
		});
	
	});
	
	
	

	//ajax 내가한것
/* 	$("#ajaxAdd").on("click", function(){
		
		const formData = new FormData();
	
//		const fileInput = $(".files")[0].files[0];
	   	const fileInput = $('.files');
	    for (var i = 0; i < fileInput.length; i++) {
	    	if (fileInput[i].files.length > 0) {
	    		for (var j = 0; j < fileInput[i].files.length; j++) {
	    			// formData에 'file'이라는 키값으로 fileInput 값을 append 시킨다.  
	    			formData.append('files', $('.files')[i].files[j]);
	    		}
	    	}
	    }
	    
		formData.append('productName',  $("#productName").val());
		formData.append('productPrice', $("#productPrice").val());
		formData.append('productCount', $("#productCount").val());
		formData.append('productDetail', $("#productDetail").summernote("code"));
		
		
		$.ajax({
			type: "POST",
			url: "./add",
		    processData: false,
		    contentType: false,
		    cache: false,
	        timeout: 600000,
	        data:formData,
			/* data: {
				productName: $("#productName").val(),
				productPrice: $("#productPrice").val(),
				productCount: $("#productCount").val(),
				productDetail: $("#productDetail").summernote("code") // $("#productDetail").val()
													//뒤에 주석처리된것과 같은건데 summernote의 value를 가지고 올수 있는 코드
			}, */
			
			/* success:function(data){
				if(data.trim() == '1'){
					alert("상품 등록 완료");
					//다시 호출해서 가지고오고
					//들어있는 값들 초기화
					getList();
					$("#productName").val("");
					$("#productPrice").val("");
					$("#productCount").val("");
					$("#productDetail").summernote("code", "");
					$("#fileResult").empty();
				}else{
					alert("상품 등록 실패");
				}
			},
			error:function(){
				alert("에러 발생");
			}
		});
		
	}); */
	 





	//summernote
	/* $('#productDetail').summernote({
		height: 300                 
	}); */

	//강사
	/* let count = 0; */

	/* $("#fileAdd").on("click", function(){
		
		//강사
		if(count > 4){
			alert('최대 5개만 가능합니다');
			return;
		}
		
		let result = '<div class="input-group">';
		result = result + '<input name="files" type="file" class="form-control files" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">';
		result = result + '<button class="btn btn-outline-secondary del" type="button" id="inputGroupFileAddon04">X</button>';
		result = result + '</div>';
		
		$("#fileResult").append(result);
		count++; */
		
		//내가 해본것(지우는것 아직 미구현)
		/* let r = '<div class="row mb-3">';
		r = r + '<label for="files" class="col-sm-2 col-form-label">File</label>';
		r = r + '<div class="col-sm-9">';
		r = r + '<input type="file" name="files" class="form-control">';
		r = r + '</div>';
		r = r + '<div class="col-sm-1">';
		r = r + '<button type="button" id="delete">X</button>';
		r = r + '</div>';
		r = r + '</div>';

		$("#fileResult").append(r);
		//잘 생각해볼것! fileResult가 먼저 생성된것이기에 이거로 이용해줘야함
		$("#delete").on("click", function(){
			$("#fileResult").empty();
		}); */
		
	//});
	
	/* $("#fileResult").on("click", ".del", function(){
		//대상의 부모를 찾아 지움
		$(this).parent().remove();
		count--;
			
	}); */
	
	
</script>

</body>
</html>