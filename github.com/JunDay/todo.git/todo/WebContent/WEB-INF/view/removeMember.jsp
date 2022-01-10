<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
<div class="container">
	<div style="width:300px; height:300px; position:absolute; left:50%; top:50%; margin-left:-150px; margin-top:-150px;">

	<form id="removeForm" method="post"action="${pageContext.request.contextPath}/member/remove">
		<table class="table table-borderless">
			<tr style="background-color: #1E90FF; text-align:center;">
					<td colspan="2"><h3>To-Do Calendar</h3></td>
			</tr>
			<tr style="background-color: #B0C4DE; text-align:center;">
				<td colspan="2"><h1>removeMember</h1></td>
			</tr>
			<tr style="text-align:center;">
				<td colspan="2"><h5>${loginMember.memberId}님 탈퇴하시겠습니까?</h5></td>
			</tr>
			<tr>
				<td>PW Check</td>
				<td><input id="memberPw" name="memberPw" type="password"></td>
			</tr>
			<tr>
				<td style="text-align:left;">
					<a class="btn btn-success" href="${pageContext.request.contextPath}/member/calendar">이전</a>
				</td>
				<td style="text-align:right;">
					<button class="btn btn-danger" id="removeBtn" type="button">회원탈퇴</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>

<script>
$('#removeBtn').click(function(){
   if($('#memberPw').val() == '') {
		alert('memberPw를 입력하세요');
		return;
	} else {
		$('#removeForm').submit();
	}
});
</script>
</body>
</html>