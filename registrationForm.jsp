<%-- 1. uri와 prefix 오타 수정 (framwork -> framework, from -> form) --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New User Registration</title>
</head>
<body>
    <%-- 2. action 경로와 modelAttribute 확인 --%>
    <form:form action="./" modelAttribute="account" method="post">
        <h1>New User Registration</h1>
        
        <div>Username: <form:input path="username" /></div>
        
        <%-- 3. form: password 붙여쓰기 수정 --%>
        <div>Password: <form:password path="password" /></div>
        
        <%-- 4. 확인용 필드는 보통 path가 다르거나 name으로 처리합니다 (예: confirmPassword) --%>
        <div>Confirm password: <form:password path="confirmPassword" /></div>
        
        <%-- 5. from -> form 오타 수정 및 닫는 태그 /> 적용 --%>
        <div>E-mail address: <form:input path="email" /></div>
        
        <div>First name: <form:input path="firstName" /></div>
        
        <div>Last name: <form:input path="lastName" /></div>
        
        <div style="margin-top:10px;">
            <button type="submit">Register</button>
        </div>
    </form:form>
</body>
</html>
