<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
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
                    <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
                   <c:if test="${not empty member}">
                    <h3>로그인 상태</h3>
                    <sprong:message code="Welcome.login" arguments="${member.username}-${ }
                    
                    
                   </c:if> 

					<c:if test="${empty member}">                    
                    <h3>비 로그인 상태</h3>
                    </c:if>
                    
                    <div id="map" style="width:500px;height:400px;"></div>
                    
                    
                    
					
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
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a51fbcc2036a3caba4c7de7ab3e51474"></script>
	<script type="text/javascript">
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		let options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
	
		let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	</script>
</body>
</html>