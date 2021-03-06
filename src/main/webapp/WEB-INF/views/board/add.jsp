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
	  		<h4 style="text-transform: uppercase;">${board} Write</h4>
		</div>
	</div>
	
	
	<div class="row mt-4">
		<form action="./add" method="post" enctype="multipart/form-data">
		  <div class="row mb-3">
		    <label for="title" class="col-sm-2 col-form-label">Title</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="title" name="title">
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="writer" class="col-sm-2 col-form-label">Writer</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="writer" name="writer" value="${member.id}" readonly>
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="contents" class="col-sm-2 col-form-label">Contents</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" id="contents" name="contents"></textarea>
		    </div>
		  </div>
		  
		  
		  <button id="fileAdd" type="button" class="btn btn-danger d-block my-4">FileAdd</button>
		  
		  <div id="fileResult"></div>

		  <button type="submit" class="btn btn-primary my-4">Write</button>
		</form>
	
	</div>
	
	
</div>	




<script type="text/javascript">

	//summernote
	//이미지 업로드 추가
	//이 부분 summernote.js에 넣어놓고 함수 호출해서 사용하는것으로 바꿨는데 이건(board/add.jsp)에는 확인용도로 놔둬놓음
	//하게되면 위에 추가해줘야함 <script type="text/javascript" src="../js/summernote.js"> <script>
	//밑에 첨부파일도 !
	
	$('#contents').summernote({
		height: 300,                 
		placeholder: '내용을 입력하세요',
		callbacks: {
			onImageUpload: function(files){//onImageUpload 시작
				//files upload한 이미지 파일객체	
				let formData = new FormData();
				formData.append("summerFiles", files[0]);
				console.log($(".note-image-input").val())
				// /board/summerFileUpload
				$.ajax({
					type: "POST",
					url: "./summerFileUpload",
					processData: false,
				    contentType: false,
					data:formData,
					
					success:function(data){
						//console.log(data.trim());
						//summernote 자체적으로 만들어주는 클래스라서 성공하면 자동적으로 이 클래스 선택해서 값을 지워주는것을 해줘야함
						//첨부파일 안넣었을때 없애주는것
						$(".note-image-input").val('');
						
						$('#contents').summernote('editor.insertImage', data.trim());
					}

				});
				
			},//onImageUpload 끝
			
			onMediaDelete:function(files){//onMediaDelete 시작
				let fileName = $(files[0]).attr("src");//<img src="">
				console.log(fileName);
				$.ajax({
					type:"GET",
					url:"./summerFileDelete",
					data:{
						fileName: fileName
					},
					
					success:function(data){
						console.log(data.trim());
					}
					
				});
			}//onMediaDelete 끝
			
		}
	});

	//강사
	let count = 0;

	$("#fileAdd").on("click", function(){
		
		//강사
		if(count > 4){
			alert('최대 5개만 가능합니다');
			return;
		}
		
		let result = '<div class="input-group">';
		result = result + '<input name="files" type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">';
		result = result + '<button class="btn btn-outline-secondary del" type="button" id="inputGroupFileAddon04">X</button>';
		result = result + '</div>';
		
		$("#fileResult").append(result);
		count++;
		
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
		
	});
	
	$("#fileResult").on("click", ".del", function(){
		//대상의 부모를 찾아 지움
		$(this).parent().remove();
		count--;
			
	});
	
	
</script>

</body>
</html>