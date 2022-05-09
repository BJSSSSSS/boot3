/**
 * 
 */
 
$("#cart").click(function(){
	
	let productNum = $(this).attr("data-pn");
	let count = 2;
	$.ajax({
		type:"post",
		url:"../cart/"+productNum+"/"+count,
		success:function(data){
			
			console.log("Data : ", data);
			if(data>0){
				let check = confirm("장바구니로 이동");	
			}else{
				alret("등록실패");
			}
			
			
		}
		
	});
	
	
});


$("#btn").click(function(){
	$.ajax({
		type:"DELETE",
		url:"../cart/1",
		success:function(data){
			console.log(data.trim());
		}
		
	});
});


 
function getList(){
	 $.ajax({
		type:"GET",
		url:"../cart/1",
		success:function(data){
			console.log(data);
			
			//console.log(data[0].cartNum);
		}
	});
}








