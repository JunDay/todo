<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style>
	body{
		background-image:url('${pageContext.request.contextPath}/image/background.jpg');
		background-size: cover;
	}
	.table-calalder tr{
		background-color:rgba(0, 0, 0, 0.7);
	}
	.table-calalder td{
		color: #D0D0D0;
	}
	.table-calalder tr{
		background-color:rgba(0, 0, 0, 0.7);
	}
	.table-calalder a{
		color: white;
	}
	.cal-td-nav1 td{
		background-color:rgba(0, 0, 0, 0.4);
		color: gray;
	}
	.cal-td-nav1 a{
		color: #000000;
	}
	.cal-td-nav1 a:hover{
		color: #000000;
	}
	.cal-td-nav2 td{
		background-color:rgba(0, 0, 0, 0.8);
		color: gray;
	}
</style>

</head>
<body>
<div class="container">
	<div style="width:300px; position:absolute; left:50%; top:50%; margin-left:-150px; margin-top:-300px;">
		<form id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
			<table class="table table-calalder">
				<tr class="cal-td-nav1" style="text-align:center;">
					<td colspan="2"><h3 style="color: white;">To-Do Calendar</h3></td>
				</tr>
				<tr class="cal-td-nav2" style="text-align:center;">
					<td colspan="2"><h1 style="color: #D0D0D0;">login</h1></td>
				</tr>
				<tr>
					<td style="text-align:center; color: white;">ID</td>
					<td><input id="memberId" type="text" name="memberId" value="admin"></td>
				</tr>
				<tr>
					<td style="text-align:center; color: white;">PW</td>
					<td><input id="memberPw" type="password" name="memberPw" value="1234"></td>
				</tr>
				<tr>
					<td style="text-align:center;" colspan="2">
						<button id="loginBtn" class="btn btn-light" type="button">로그인</button>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;" colspan="2">
						<a class="btn btn-info" href="${pageContext.request.contextPath}/addMember">회원가입</a>
					</td>
				</tr>
			</table>
		</form>
		
		<h2 style="background-color:rgba(0, 0, 0, 0.7); color: #D0D0D0; padding: 15px; text-align: center">공지사항<a href="${pageContext.request.contextPath}/noticeList" style="font-size: 7px;">more</a></h2>
		<table class="table table-calalder">
			<tr>
				<td>noticeTitle</td>
				<td>createDate</td>
			</tr>
			<c:forEach var="n" items="${noticeList}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
					<td>${n.createDate}</td>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align: center;">
			<a href="${pageContext.request.contextPath}/adminLogin" class="btn btn-light">[관리자 로그인]</a>
			<!-- 1) /adminLogin, AdminLoginController.doGet(), adminLogin.jsp -->
			<!-- 2) /adminLogin, dminLoginController.doPost(), /admin/adminIndex, AdminIndexController.doGet(), adminIndex.jsp -->
		</div>
	</div>
</div>
<script>
$('#loginBtn').click(function(){
   if($('#memberId').val() == '') {
		alert('memberId를 입력하세요');
		return;
	} else if($('#memberPw').val() == '') {
		alert('memberPw를 입력하세요');
		return;
	} else {
		$('#loginForm').submit();
	}
});
</script>
</body>
</html>