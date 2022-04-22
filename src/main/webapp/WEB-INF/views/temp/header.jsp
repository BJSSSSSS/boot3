<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="container-fluid">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Navbar</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/board/list">Board</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/product/list">Product</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Dropdown
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link disabled">Disabled</a>
	        </li>
	      </ul>
	      
	      <ul class="navbar-nav mb-2 mb-lg-0">
	      	<c:choose>
	      		<c:when test="${not empty member}">
				    <li class="nav-item">
			          <a class="nav-link active" aria-current="page" href="/member/mypage">
			          Mypage <i class="bi bi-person-circle"></i>
			          </a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link active" href="/member/logout">
			          LogOut <i class="bi bi-unlock"></i>
			          </a>
			        </li>      		
	      		</c:when>
	      		<c:otherwise>
			        <li class="nav-item">
			          <a class="nav-link active" aria-current="page" href="/member/join">
			          Join <i class="bi bi-person-plus-fill"></i>
			          </a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link active" href="/member/login">
			          Login <i class="bi bi-lock-fill"></i>
			          </a>
			        </li>	  
		        </c:otherwise>
	        </c:choose>    
	      </ul>
	      
	    </div>
	  </div>
	</nav>
</header>