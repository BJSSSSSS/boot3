
 
 
 
//summernote 초기화 함수
function summernoteInit(selector, code){//selector에 id를 보낼것임
	
	//summernote
	//summernote
	//이미지 업로드 추가
	$('#'+selector).summernote({
		height: 300,                 
		placeholder: '내용을 입력하세요',
		callbacks: {
			onImageUpload: function(files){//onImageUpload 시작
				//files upload한 이미지 파일객체	
				let formData = new FormData();
				formData.append("summerFiles", files[0]);
				//console.log($(".note-image-input").val())
				// /board/summerFileUpload
				$.ajax({
					type: "POST",
					url: "../summernote/summerFileUpload",
					processData: false,
				    contentType: false,
					data:formData,
					
					success:function(data){
						//console.log(data.trim());
						//summernote 자체적으로 만들어주는 클래스라서 성공하면 자동적으로 이 클래스 선택해서 값을 지워주는것을 해줘야함
						//첨부파일 안넣었을때 없애주는것
						$(".note-image-input").val('');
						
						$('#'+selector).summernote('editor.insertImage', data.trim());
					}

				});
				
			},//onImageUpload 끝
			
			onMediaDelete:function(files){//onMediaDelete 시작
				let fileName = $(files[0]).attr("src");//<img src="">
				console.log(fileName);
				$.ajax({
					type:"GET",
					url:"../summernote/summerFileDelete",
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

	
	$('#productDetail').summernote('code', code);
	
}
 













//기존 add는 ajax를 사용하려고 해서 update에서는 동기방식으로 하려고 다 지워버림
	//+ 다른곳에 js 만들어서 불러오게 만들어 봄
	
	/* if($("#sale").val() == 1){
		$("#flexRadioDefault1").attr("checked", checked);
		$("#flexRadioDefault2").attr("checked", off);
	}else{
		$("#flexRadioDefault1").attr("checked", off);
		$("#flexRadioDefault2").attr("checked", checked);
	} */
