<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업데이트 창</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 800px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 3px
}

div {
	text-align: center;
}

#wrap {
	width: 800px;
	margin: auto;
}

.bg {
	background-color: #99ccff;
}
</style>
<script type="text/javascript">
	function save_go(f) {
		var k = "${guestBook2VO.pwd}";
		var k2 = "${guestBook2VO.idx}";
		if (f.pwd.value == k) {
			var chk = confirm("정말 수정할까요?");
			if (chk) {
				f.action = "gb2_update_ok.do"
				f.submit();
			} else {
				history.go(-1);
			}
		} else {
			alert("비밀번호 틀림");
			f.pwd.value = "";
			f.pwd.focus();
			return;
		}
	}
	
	function cancle_go(f) {
		f.action = "gb2_list.do";
		f.submit();
	}
	
</script>
</head>
<body>
	<div id="wrap">
		<h2>방명록 : 수정화면</h2>
		<hr>
		<p>
			[<a href="gb2_list.do">목록으로 이동</a>]
		</p>
		<!--
			<form> 태그의 enctype 속성은 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시합니다.
			이 속성은 <form> 요소의 method 속성값이 “post”인 경우에만 사용할 수 있습니다.
		  -->
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="bg">작성자</td>
					<td><input type="text" name="name" size="20"
						value="${guestBook2VO.name}"></td>
				</tr>
				<tr>
					<td class="bg">제 목</td>
					<td><input type="text" name="subject" size="20"
						value="${guestBook2VO.subject}"></td>
				</tr>
				<tr>
					<td class="bg">email</td>
					<td><input type="text" name="email" size="20"
						value="${guestBook2VO.email}"></td>
				</tr>
				<tr>
					<td class="bg">첨부파일</td>
					<td><c:choose>
							<c:when test="${empty guestBook2VO.f_name }">
								<input type="file" name="f_param">
								<b>이전 파일 없음</b>
								<input type="hidden" name="old_f_name" value="">
							</c:when>
							<c:otherwise>
								<input type="file" name="f_param">
								<b>이전 파일명 (${guestBook2VO.f_name})</b>
								<input type="hidden" name="old_f_name"
									value="${guestBook2VO.f_name}">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td class="bg">비밀번호</td>
					<td><input type="password" name="pwd" size="20"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><textarea rows="10" cols="60" name="content"
							id="content">${guestBook2VO.content }</textarea></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
						<input type="button" value="수정" onclick="save_go(this.form)" />
						<input type="hidden" name="idx"	value="${guestBook2VO.idx}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소" onclick="cancle_go(this.form)" />
						<!-- <input type=“reset”>는 모든 폼(form) 요소의 값을 초깃값으로 되돌리는 리셋 버튼(reset button)을 정의합니다. -->
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		crossorigin="anonymous"></script>
	<script src="resources/js/summernote-lite.js"></script>
	<script src="resources/js/lang/summernote-ko-kr.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#content").summernote({
				lang : "ko-KR",
				height : 300,
				focus : true,
				callbacks : {
					onImageUpload : function(files, editor) {
						for (var i = 0; i < files.length; i++) {
							sendImage(files[i], editor)
						}
					}
				}
			});
		});

		function sendImage(file, editor) {
			var frm = new FormData();
			frm.append("upload", file);
			// 비동기 통신
			$.ajax({
				url : "${pageContext.request.contextPath}/view/saveImage.jsp",
				data : frm, // 전달하고자 하는 파라미터 값
				type : "post", // 전송 방식
				contentType : false,
				processData : false,
				dataType : "json",
			}).done(function(data) {
				$("#content").summernote("editor.insertImage", data.img_url);
			});
		}
	</script>
</body>
</html>