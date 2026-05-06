<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Join</title>
    <c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
</head>
<body id="page-top">
    <div id="wrapper">
        <c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
                
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800">Join Page</h1>
                    
                    <div>
                        <%-- modelAttribute는 Controller에서 보낸 이름과 일치해야 함 --%>
                        <form:form action="./join" modelAttribute="memberDTO" method="post" enctype="multipart/form-data">
                            
                            <div class="form-group">
                                <label for="username">Username</label>
                                <form:input path="username" cssClass="form-control" id="username" />
                                <form:errors path="username" cssClass="text-danger" element="div" />
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <form:password path="password" cssClass="form-control" id="password" />
                                <form:errors path="password" cssClass="text-danger" element="div" />
                            </div>

                            <div class="form-group">
                                <label for="passwordCheck">Password Check</label>
                                <%-- 비밀번호 확인은 DTO에 필드가 없다면 일반 input 사용 --%>
                                <input type="password" id="passwordCheck" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="name">Name</label>
                                <form:input path="name" cssClass="form-control" id="name" />
                                <form:errors path="name" cssClass="text-danger" element="div" />
                            </div>

                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <form:input path="phone" cssClass="form-control" id="phone" placeholder="010-0000-0000" />
                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" cssClass="form-control" id="email" />
                            </div>

                            <div class="form-group">
                                <label for="birth">Birth</label>
                                <form:input type="date" path="birth" cssClass="form-control" id="birth" />
                            </div>
                            
                            <div class="form-group">
                                <label>첨부파일 (Profile Image)</label>
                                <%-- 파일은 path 대신 일반 input name="attach" 사용 --%>
                                <input type="file" name="attach" class="form-control">
                            </div>
                                                                              
                            <button type="submit" class="btn btn-primary">Join Now</button>
                        </form:form>
                    </div>
                </div>
            </div>
            <c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
        </div>
    </div>
    <c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
</body>
</html>