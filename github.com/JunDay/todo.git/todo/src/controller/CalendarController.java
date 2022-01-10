package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CalendarService;
import service.TodoService;
import vo.Member;

@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	private CalendarService calendarService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] CalendarController.doGet() \"Started\"");
		
		// 1. 이전, 다음 버튼 클릭 시 : 이전, 다음 달을 확인하기 위한 값 가져오기
		String currentYear = request.getParameter("currentYear");
		String currentMonth = request.getParameter("currentMonth");
		String option = request.getParameter("option");
		System.out.println(" ├[value] currentYear : "+currentYear);
		System.out.println(" ├[value] currentMonth : "+currentMonth);
		System.out.println(" ├[value] option : "+option);
		
		// 2. 요청한 멤버 값 가져오기
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		System.out.println(" ├[value] memberId : "+memberId);
		
		// 3. 달력에 대한 정보 가져오기
		calendarService = new CalendarService();
		Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
		
		// 4. 현재 선택된 날짜 데이터 설정
		request.setAttribute("targetYear", map.get("targetYear"));		// 선택된 년도
		request.setAttribute("targetMonth", map.get("targetMonth"));	// 선택된 달
		request.setAttribute("endDay", map.get("endDay"));				// 선택된 달의 마지막 날
		System.out.println(" ├[value] targetYear : "+map.get("targetYear"));
		System.out.println(" ├[value] targetMonth : "+map.get("targetMonth"));
		System.out.println(" ├[value] endDay : "+map.get("endDay"));
		
		// 5. 현재 선택된 달의 앞/뒤 필요한 공백 정보
		request.setAttribute("startBlank", map.get("startBlank"));		// 선택된 달의 앞 공백
		request.setAttribute("endBlank", map.get("endBlank"));			// 선택된 달의 뒤 공백
		System.out.println(" ├[value] startBlank : "+map.get("startBlank"));
		System.out.println(" ├[value] endBlank : "+map.get("endBlank"));
		
		// 6. 현재 선택된 달의 todo 목록 정보
		request.setAttribute("todoList", map.get("todoList"));			// 선택된 달의 각 일에 대한 todo 요약 정보
		
		
		System.out.println("-[Debug] CalendarController.doGet() \"Finished\"");
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}
}
