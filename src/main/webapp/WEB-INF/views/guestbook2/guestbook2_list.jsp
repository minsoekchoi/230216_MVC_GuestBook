<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div>
		<form action="guestbook2_onelist.do" method="post">
		<h2> 방 명 록 </h2>
		<hr>
		<p>[<a href="gb2_write.do">방명록 쓰기</a>]</p>
		<table>
			<thead>
				<tr style="background-color: #99ccff"><th>번호</th><th>작성자</th><th>제목</th><th>작성일</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr><td colspan="4"><h2>원하는 정보가 존재하지 않습니다.</h2> </td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="k" varStatus="vs">
							<tr>
								<td>${vs.count}</td>
								<td>${k.name}</td>
								<td><a href="gb2_onelist.do?idx=${k.idx}">${k.subject}</a></td>
								<td>${k.regdate.substring(0,10)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>