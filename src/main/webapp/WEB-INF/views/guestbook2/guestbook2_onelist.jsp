<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">
	function update_go(f) {
		f.action="gb2_update.do";
		f.submit();		
	}
	function delete_go(f) {
		f.action="gb2_delete.do";
		f.submit();
	}
</script>
</head>
<body>
<div>
		<h2>방명록 : 내용화면</h2>
		<hr>
		<p>[<a href="gb2_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${guestBook2VO.name }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${guestBook2VO.subject }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${guestBook2VO.email }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">첨부파일</td>
					<c:choose>
						<c:when test="${! empty guestBook2VO.f_name}">
							<td>
							<img src="<c:url value='/resources/upload/${guestBook2VO.f_name}'/>" width="200px"><br>
							<a href="gb2_down.do?f_name=${guestBook2VO.f_name}">${guestBook2VO.f_name}</a>
							</td>
						</c:when>
						<c:otherwise>
							<td><b>첨부 파일 없음</b></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr style="text-align: left; ">
					<td colspan="2"><pre style="padding-left: 15px" >${guestBook2VO.content}</pre></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="idx" value="${guestBook2VO.idx}">
							<%-- <input type="hidden" name="pwd" value="${guestBook2VO.pwd}"> --%>
							<!-- <input type="hidden" name="cmd" value="1"> -->
							<!-- cmd 를 활용할 경우에는 cmd도 부여하여 가져가 주여야 한다. -->
							<!-- ?? cmd 가 1이 아닌 경우가 있나?? -->
							<!-- 삭제를 위한 1이구만  -->
							<!-- 수정은 2가되겠군 -->
							<input type="button" value="수정" onclick="update_go(this.form)">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;								   
							<input type="button" value="삭제" onclick="delete_go(this.form)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>