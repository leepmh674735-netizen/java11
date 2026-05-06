<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
   
	
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
				
				<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Create Page</h1>
                    
                    <div>
                    	<form:form method="post" modelAttribute="memberDTO" enctype="multipart/form-data">
						  						  					  					 
						  <div class="form-group">
						    <label for="name">Name</label>
						    <form:input path="name" class="form-control" id="name"/>
						    <form:errors path="name"></form:errors>
						  </div>
						  <div class="form-group">
						    <label for="phone">Phone</label>
						    <form:input path="phone" class="form-control" id="phone"/>
						    <form:errors path="phone"></form:errors>
						  </div>
						  <div class="form-group">
						    <label for="email">Email</label>
						    <form:input path="email" class="form-control" id="email"/>
						    <form:errors path="email"></form:errors>
						  </div>						  
                    	
						  <div class="form-group">
						    <label for="birth">Birth</label>
						    <input type="date" value="${memberDTO.birth}" name="birth" class="form-control" id="birth">
						    <form:errors path="birth"></form:errors>
						  </div>
						  <div class="form-group">
						  	<label>첨부파일</label>
						  	<input type="file" name="attach" class="form-control">
						  </div>
						  <button type="submit" class="btn btn-primary">Submit</button>
                    	</form:form>
                    </div>

                </div>
                <!-- End Page container-fluid -->
                
			</div>
			<!-- End page Content -->
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
		<!-- End Content wrapper -->
	</div>
	<!-- End Wrapper -->
	
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
	
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
	<script>
      $('#contents').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 100
      });
    </script>
	<!-- <script src="/js/member/join.js"></script> -->

</body>
</html>