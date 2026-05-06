<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
</head>
<body id="page-top">
    <div id="wrapper">
        <c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
                
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800">Login</h1>
                    
                    <div>
                        <%-- 회원가입 폼이라면 action="./join"으로 변경하세요 --%>
                        <form:form action="./login" modelAttribute="memberDTO" method="post">
                            <div class="form-group">
                                <label for="username">ID</label>
                                <form:input path="username" cssClass="form-control" id="username" placeholder="Enter ID"/>
                                <%-- 에러 발생 시 빨간 글씨로 출력됨 --%>
                                <form:errors path="username" cssClass="text-danger" element="div" />
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <form:password path="password" cssClass="form-control" id="password" placeholder="Enter Password" />
                                <%-- 오타 수정: erroes -> errors --%>
                                <form:errors path="password" cssClass="text-danger" element="div" />
                            </div>                      
                            
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form:form>
                    </div>
                </div>
            </div>
            <c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
        </div>
    </div>
    
    <c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
    <script>
      // 필요 시 summernote 초기화 (로그인 창에는 보통 필요 없으나 유지합니다)
      $('#contents').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 100
      });
    </script>
</body>
</html>