/**
 * 글 작성 시 첨부파일 관리하는 JS
 */

	let count = 0;

	function fileAddInit(c){
		 //지역변수에서 전역변수로
		 //let count = c;
		 count = c;
		
		 $("#fileAdd").on("click", function(){
		     
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
		     count++;
		     
		 });
		 
		 $("#fileResult").on("click", ".del", function(){
		     //대상의 부모를 찾아 지움
		     $(this).parent().remove();
		     count--;
		         
		 });
 	}
 	
 	
 	function fileDeleteInit(){
		
		$(".del").click(function(){
			
			let check = window.confirm("영구히 삭제 됩니다. 삭제하시겠습니까?"); //check에 boolean 담김
			if(!check){//사용자를 생각해서 DELETE 잘못 눌렀을때 방지
				return;
			}
			
			let fileNum = $(this).attr("data-num");
			let selector = $(this);
			//console.log(this);//event.target 클릭한 element
			console.log(selector);
			$.ajax({
				
				type:"POST",
				url:"./fileDelete",
				data:{
					fileNum:fileNum
				},
				success:function(data){
					//console.log(this);//ajax 객체
					//console.log(selector);
					if(data.trim() == 1){
						$(selector).parent().remove();
						//전역변수로 선언 된 count--, 같이 쓰려고
						count--;
					}else{
						alert("파일 삭제가 실패!!")
					}
				},
				error:function(){
					alret("File 삭제 실패");
				}
				
			})
			
			
		});
		
	}
