<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 검색된 도서 최종 확인 페이지 -->
	<h1>'${bName.bName }' 책 검색 결과</h1>
   	 		<table class="table table-hover">
		
			<thead>
				<th>도서 번호</th>
				<th>도서명</th>
				<th>저자</th>
				<th>출판사</th>
				<th>카테고리</th>
				<th>대여 상태</th>
				<th>대여하기</th>
				
				
			</thead>
			<tbody>
					<tr>
						<td>${bName.bNum}</td>
						<td>${bName.bName}</td>
						<td>${bName.bWriter}</td>
						<td>${bName.bPub}</td>
						<td>${bName.bCategory}</td>
						<td>${bName.checkOut}</td>
						<td><c:if test="${book.checkOut eq 0}">
    			<a href="http://localhost:8181/electronic_library/book/rent_check.jsp"><input type="button" value="대여"></a>
				</c:if></td>
						
					</tr>
			</tbody>
		
			</table>

</body>
</html>