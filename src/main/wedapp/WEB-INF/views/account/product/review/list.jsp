<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<table class="table table-dark">
  <thead>
  	<tr>
  		<th>Contents</th>
  		<th>Writer</th>
  		<th>Date</th>
  		<th></th>
  	</tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="r">
  		<tr>
  			<td>${r.reviewContents}</td>
  			<td>${r.username}</td>
  			<td>
  				<fmt:parseDate value="${r.reviewDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
  				<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm" />
  			</td>
  			<td data-review-num="${r.reviewNum}">
  				<button class="btn btn-success review_update">수정</button>
  				<button class="btn btn-danger review_delete">삭제</button>
  			</td>
  			
  		</tr>
  	</c:forEach>
  </tbody>
  
</table>