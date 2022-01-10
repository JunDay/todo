package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.*;

@WebServlet("/member/todoList")
public class TodoController extends HttpServlet {
	private TodoService todoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] TodoController.doGet() \"Started\"");
		
		// 1. 멤버, 날짜 데이터 가져오기
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		System.out.println(" ├[value] memberId : "+memberId);
		System.out.println(" ├[value] y : "+y);
		System.out.println(" ├[value] m : "+m);
		System.out.println(" ├[value] d : "+d);
		
		// 2. 달, 일 가공 (2자리 수 유지 필요)
		if(m.length()<2) {
			System.out.print(" ├[value] m : "+m);
			m = "0"+m;
			System.out.println(" -> : "+m);
		}
		if(d.length()<2) {
			System.out.print(" ├[value] d : "+d);
			d = "0"+d;
			System.out.println(" -> : "+d);
		}
		
		// 2. 데이터 가공 (y, m, d -> todoDate & todoDate -> todo)
		String todoDate = y+"-"+m+"-"+d;
		System.out.println(" ├[value] todoDate : "+todoDate);
		
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		System.out.println(" ├[value] todo : "+todo.toString());
		
		// 3. 투두 목록 조회 서비스 실행
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		request.setAttribute("targetYear", y);	// 선택된 년도
		request.setAttribute("targetMonth", m);	// 선택된 달
		request.setAttribute("targetDate", d);	// 선택된 달
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("todoList", todoList);
		
		System.out.println("+[Debug] TodoController.doGet() \"Finished\"");
		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}
}
