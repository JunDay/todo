<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	body{
		background-image:url('${pageContext.request.contextPath}/image/background.jpg');
		background-size: cover;
	}
	.table-calalder tr{
		background-color:rgba(0, 0, 0, 0.7);
	}
	.table-calalder a{
		color: white;
	}
	.cal-td td{
		color: white;
	}
	.cal-td-nav1 td{
		background-color:rgba(0, 0, 0, 0.4);
		color: gray;
	}
	.cal-day a{
		color: #64D6FF;
		font-size: 20px;
	}
	.cal-day a:hover{
		color: #1E82FF;
		font-size: 20px;
	}
	.cal-td-nav1 a:hover{
		color: #D7F1FA;
	}
	.cal-td-nav2 td{
		background-color:rgba(0, 0, 0, 0.8);
		color: gray;
	}
	.todos{
		color: white;
	}
</style>
</head>
<body>
<div class="container" style="margin-top:100px; margin-bottom:20px">	
	<!-- JSTL for문 -->
	<div>
	<table class="table table-calalder">
		<tr class="cal-td-nav1">
			<td style="text-align:left;"><h1><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre">◀</a></h1></td>
			<td style="text-align:center; color: white;" colspan="5"><h1>${targetYear}년 ${targetMonth}월</h1></td>
			<td style="text-align:right;"><h1><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next">▶</a></h1></td>
		</tr>
		<tr class="cal-td-nav2">
			<td style="text-align:left" colspan="3">${targetMonth}월의 총 할 일 : ${todoList.size()}</td>
			<td style="text-align:right" colspan="4">
				${loginMember.memberId}님 반갑습니다.
				<a class="btn btn-light" style="color: black; opacity: 0.7;" href="${pageContext.request.contextPath}/logout">로그아웃</a>
				<a class="btn btn-light" style="color: black; opacity: 0.7;" href="${pageContext.request.contextPath}/member/remove">회원탈퇴</a>
			</td>
		</tr>
		<tr class="cal-td" style="text-align:center">
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
			<c:forEach var="i" begin="1" end="${startBlank+endDay+endBlank}" step="1">
				
				<c:if test="${i-startBlank <= 0 || i-startBlank > endDay}">
					<td>&nbsp;</td>
				</c:if>
				
				<c:if test="${i-startBlank >= 1 && i-startBlank <= endDay}">
					<td class="cal-day" width="100px" height="100px"><a href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
						<div>
							<!-- 날짜별 일정 -->   
							<c:forEach var="todo" items="${todoList}">
								<c:if test="${i-startBlank == todo.todoDate.substring(8)}">
									<div class="todos" style="color:${todo.fontColor}; background-color:rgba(255, 255, 255, 0.3); padding-left: 3px;">${todo.todoContent}</div>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</c:if>
				
				<c:if test="${i%7 == 0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	</div>
</div>
</body>
</html>