<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
    <c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
    <!-- Summernote CSS -->
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
                    <h1 class="h3 mb-4 text-gray-800">Login</h1>
                    
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <%-- 
                                action: SecurityConfig의 loginProcessingUrl과 일치해야 함
                                modelAttribute: 컨트롤러에서 넘겨준 객체 이름 (보통 'member' 또는 'memberVO')
                            --%>
                            <form:form action="/member/login" method="post" modelAttribute="memberDTO">
                                
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <form:input path="username" cssClass="form-control" id="username" placeholder="아이디를 입력하세요"/>
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <form:password path="password" cssClass="form-control" id="password" placeholder="비밀번호를 입력하세요"/>
                                </div>

                                <div class="form-group">
                                    <div class="custom-control custom-checkbox small">
                                        <input type="checkbox" class="custom-control-input" id="remember" name="remember" value="true">
                                        <label class="custom-control-label" for="remember">아이디 기억하기</label>
                                    </div>
                                </div>
                                 
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </form:form>
                        </div>
                    </div>

                </div>
                <!-- End Page container-fluid -->
            </div>
            <c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
        </div>
    </div>
    
    <c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
    
    <!-- Summernote JS -->
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
    <script>
      $(document).ready(function() {
          // #contents 아이디를 가진 textarea가 있을 경우 실행
          if($('#contents').length) {
              $('#contents').summernote({
                placeholder: '내용을 입력하세요',
                tabsize: 2,
                height: 300
              });
          }
      });
    </script>
</body>
</html>