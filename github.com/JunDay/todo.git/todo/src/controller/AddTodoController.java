package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.*;


@WebServlet("/member/addTodo")
public class AddTodoController extends HttpServlet {
	private TodoService todoService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] AddTodoController.doPost() \"Started\"");
		
		// 1. 입력받은 데이터 초기화
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		// 2. 데이터 가공
		Todo todo = new Todo();
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		System.out.println(" ├[value] memberId : "+todo.getMemberId());
		System.out.println(" ├[value] todoDate : "+todo.getTodoDate());
		System.out.println(" ├[value] todoContent : "+todo.getTodoContent());
		
		// 3. 투두 추가 메서드 실행
		todoService = new TodoService();
		todoService.insertTodo(todo);
		
		// 4. 날짜 슬라이싱 (todoDate -> y, m, d)
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(8,10);
		
		System.out.println("-[Debug] CalendarController.doGet() \"Finished\"");
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}

}