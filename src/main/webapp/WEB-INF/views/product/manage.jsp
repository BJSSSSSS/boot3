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
  <style type="text/css">
	.detail{
		cursor: pointer;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<!-- header -->
<c:import url="../temp/header.jsp"></c:import>
	
	<div class="container">
	
		<div class="row">
			<!-- 이렇게도 가능하다 코드의 재활용 -->
			<c:import url="../common/productList.jsp"></c:import>
			<form action="./manage" id="frm">
				<input type="hidden" name="pn" id="pn" value="${pager.pn}">
			</form>
		</div>
		<div class="col-1">
			<a href="./add" type="button" class="btn btn-outline-primary">WRITE</a>
		</div>

	</div>

<c:import url="../temp/header_script.jsp"></c:import>	

<script type="text/javascript">
	
	$(".pager").click(function(){
		let pn = $(this).attr("data-pn");
		$("#pn").val(pn);
		$("#frm").submit();
	})
	
	$(".detail").click(function(){
		let num = $(this).attr("data-num");
		location.href="./manageDetail?productNum="+num;
	})

</script>

</body>
</html>