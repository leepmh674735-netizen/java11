<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
					<h1 class="h3 mb-4 text-gray-800">Product List Page</h1>

					<div class="row">
						<c:forEach items="${list}" var="p">
							<div class="col-4">
								<div class="card" style="width: 18rem;">
									<img src="/files/${name}/${p.productFileDTO.fileName}"
										class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title">${p.productName}</h5>

										<a href="./detail?productNum=${p.productNum}"
											class="btn btn-primary">상세보기</a>
									</div>
								</div>
							</div>
						</c:forEach>

						<a href="./create" class="btn btn-danger">상품등록</a>
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

</body>
</html>