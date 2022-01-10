package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class CalendarService {
	private TodoDao todoDao;
	
	/* 선택된 달에 대한 정보들 조회 */
	/* 반환 값 : 달에 대한 정보 Map 객체 */
	// option : pre, next
	public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option){
		System.out.println("+[Debug] CalendarService.getTargetCalendar() \"Started\"");	
		
		// 0. 선택된 달에 대한 데이터를 위한 객체 생성
		Map<String, Object> map = new HashMap<>();
		
		/* [1] 달력을 표시하는데 필요한 부분 */
		// 1. 현재의 시간, 날짜 데이터 리턴
		Calendar today = Calendar.getInstance();
		
		// 2. 매개변수가 있는 경우 : 특정 년, 월이 선택됐을 시 (1~12의 값 사용, 실제로 출력할 때는 0~11의 값 사용)
		if(currentYear != null && currentMonth != null) {
			// 2-0. 임시 year, month 변수 생성
			int y = 0;
			int m = 0;
			
			// 2-1. 전달받은 값 초기화
			y = Integer.parseInt(currentYear);
			m = Integer.parseInt(currentMonth);
			
			// 2-2. 이전 버튼
			if(option != null && option.equals("pre")) {
				m -= 1;
				// 2-2-1. 달의 값이 0일 때, 1월에서 이전년도 12월로 이동
				if(m == 0) {
					y--;
					m = 12;
					
				}
			// 2-3. 다음 버튼
			} else if(option != null && option.equals("next")) {
				m += 1;
				// 2-3-1. 달의 값이 13일 때, 12월에서 내년 1월로 이동
				if(m == 13) {
					y++;
					m = 1;
				}
			}
			// 2-4. 처리된 y, m 값을 현재 날짜로 설정
			today.set(Calendar.YEAR, y);
			today.set(Calendar.MONTH, m-1);	// Calendar는 0~11을 사용
		}
		
		// 3. '일'을 1로 설정 (달의 전체 정보를 표시, 일에 대한 정보 필요 X)
		today.set(Calendar.DATE, 1); // today객체 오늘의 정보 -> 같은 달 1일로 변경
		
		// 4. 처리된 today를 가공 (today -> 년, 월, 달의 끝 일) 
		int targetYear = today.get(Calendar.YEAR);
		int targetMonth = today.get(Calendar.MONTH)+1;
		int endDay = today.getActualMaximum(Calendar.DATE);
		
		// 5. 달의 앞, 뒤 공백의 개수
		int startBlank = 0; // 타겟이 되는 달의 1일의 요일 -> 일요일이면 0, 월요일이면 1, ..
		startBlank = today.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(" ├[value] startBlank : "+startBlank);
		
		int endBlank = 0; // 전체의 <td>의 개수 = startBlank+endDay+endBlank
		endBlank = 7-(startBlank+endDay)%7;
		if(endBlank == 7) {
			endBlank = 0;
		}
		
		// 6. 캘린더 페이지에 표시할 데이터 가공 (year, month, ... -> map)
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		map.put("endDay", endDay);
		map.put("startBlank", startBlank);
		map.put("endBlank", endBlank);
		
		System.out.println(" ├[value] targetYear : "+map.get("targetYear"));
		System.out.println(" ├[value] targetMonth : "+map.get("targetMonth"));
		System.out.println(" ├[value] endDay : "+map.get("endDay"));
		System.out.println(" ├[value] startBlank : "+map.get("startBlank"));
		System.out.println(" ├[value] endBlank : "+map.get("endBlank"));
		
		/* [2] 일의 todo 목록을 표시하는데 필요한 부분 */
		// 0. todo의 목록을 반환하기 위한 객체 생성
		List<Todo> list = null;
		Connection conn = null;
		
		// 1. todo의 요약 리스트 조회
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			Todo todo = new Todo();
			
			todo.setMemberId(memberId);
			
			// 1-1. todoDate를 위한 년, 월 데이터 가공 (int -> String)
			String strYear = ""+targetYear;
			String strMonth = ""+targetMonth;
			
			// 1-2. 10월 미만인 경우 앞에 0추가
			if(targetMonth < 10) {
				strMonth = "0"+targetMonth;
			}
			// 1-3. todoDate 값 대입, 데이터 가공
			todo.setTodoDate(strYear+"-"+strMonth); // 문지+숫자 => 문자
			// 1-4. 해당 달의 todo List 조회, list에 대입
			list = todoDao.selectTodoListByMonth(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 2. todo의 List를 map에 대입
		map.put("todoList", list);
		
		System.out.println("-[Debug] CalendarService.getTargetCalendar() \"Finished\"");	
		return map;
	}
}
