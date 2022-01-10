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
	.table-content td{
		color: white;
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
<div class="container" style="margin-top:100px; margin-bottom:20px">
	<table class="table table-calalder">
		<tr class="cal-td-nav1">
			<td><a class="btn btn-light" href="${pageContext.request.contextPath}/member/calendar">이전</a></td>
			<td style="text-align:center;" colspan="5"><h1>${targetYear}년 ${targetMonth}월 ${targetDate}일</h1></td>
			<td></td>
		</tr>
		<tr class="cal-td-nav2">
			<td style="text-align:left" colspan="3">${targetDate}일의 총 할 일 : ${todoList.size()}</td>
			<td style="text-align:right" colspan="4">
				${loginMember.memberId}님 반갑습니다.
				<a class="btn btn-light" style="color: black; opacity: 0.7;" href="${pageContext.request.contextPath}/logout">로그아웃</a>
				<a class="btn btn-light" style="color: black; opacity: 0.7;" href="${pageContext.request.contextPath}/member/remove">회원탈퇴</a>
			</td>
		</tr>
	</table>
	<div style="background-color:rgba(0, 0, 0, 0.7);">
		<h1 style="color: #D0D0D0; padding: 15px;">일정 목록</h1>
		<table class="table table-content">
			<tr style="text-align:center; background-color:rgba(0, 0, 0, 0.8);">
				<td style="width: 15%;">투두날짜</td>
				<td style="width: 40%;">내용</td>
				<td>작성일</td>
				<td>수정일</td>
				<td width="90px">수정</td>
				<td width="90px">삭제</td>
			</tr>
			<c:if test="${empty todoList}">
				<tr>
					<td colspan="6" style="text-align: center; font-size: 20px;">내용 없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty todoList}">
				<c:forEach var="t" items="${todoList}">
					<tr style="text-align: center;">
						<td style="color: ${t.fontColor};">${t.todoDate}</td>
						<td style="color: ${t.fontColor}; text-align: left;">${t.todoContent}</td>
						<td>${t.createDate}</td>
						<td>${t.updateDate}</td>
						<td><a class="btn btn-warning" href="">수정</a></td>
						<td><a class="btn btn-danger" href="">삭제</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<h1 style="color: #D0D0D0; padding: 15px;">일정 추가</h1>
		<form id="addTodoForm" method="post" action="${pageContext.request.contextPath}/member/addTodo">
			<table class="table table-content">
				<tr style="text-align:center; background-color:rgba(0, 0, 0, 0.8);">
					<td width="110px">날짜</td>
					<td>할 일</td>
					<td>색상</td>
					<td width="90px">추가</td>
				</tr>
				<tr style="text-align:center;">
					<td>${todoDate}<input type="hidden" name="todoDate" value="${todoDate}" readonly="readonly"></td>
					<td><textarea id="todoContent" rows="3" cols="100%" name="todoContent"></textarea></td>
					<td><input type="color" name="fontColor"></td>
					<td><button id="addTodoBtn" class="btn btn-success" type="button">추가</button></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script>
$('#addTodoBtn').click(function(){
   if($('#todoContent').val() == '') {
		alert('todoContent를 입력하세요');
		return;
	} else {
		$('#addTodoForm').submit();
	}
});
</script>
</body>
</html>