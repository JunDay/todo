package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import vo.*;

public class TodoDao {
	
	/* 달의 투두 목록 조회 */
	/* 반환 값 : 달의 투두 목록 객체 */
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException{
		System.out.println("+[Debug] TodoDao.selectTodoListByMonth() \"Started\"");
		
		// 0. 해당 달의 투두 목록을 위한 반환 객체 생성
		List<Todo> list = new ArrayList<Todo>();
		
		// 1. 달의 투두 목록을 조회하는 쿼리 생성
		String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 투두 목록 조회 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		// 3. 데이터 가공 (rs -> list)
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent5"));
			t.setFontColor(rs.getString("fontColor"));
			list.add(t);
		}
		
		// 4. 사용 자원 반환
		rs.close();
		stmt.close();
		
		System.out.println("-[Debug] TodoDao.selectTodoListByMonth() \"Finished\"");
		return list;
	}
	
	/* 투두 삽입 */
	/* 반환 값 : 없음 */
	public void insertTodo(Connection conn, Todo todo) throws SQLException {
		System.out.println("+[Debug] TodoDao.insertTodo() \"Started\"");
		
		// 1. 입력받은 투두 삽입 쿼리 생성
		String sql = TodoQuery.INSERT_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		stmt.setString(3, todo.getTodoContent());
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 투두 삽입 쿼리 실행
		stmt.executeUpdate();
		
		// 3. 사용 자원 반환
		stmt.close();
		
		System.out.println("-[Debug] TodoDao.insertTodo() \"Finished\"");
	}
	
	/* 투두 리스트 조회 */
	/* 반환 값 : 일의 투두 목록 객체 */
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException{
		System.out.println("+[Debug] TodoDao.selectTodoListByDate() \"Started\"");
		
		// 0. 해당 일의 투두 목록을 위한 반환 객체 생성
		List<Todo> dayTodoList = new ArrayList<Todo>();
		
		// 1. 일의 투두 목록 조회 쿼리 생성
		String sql = TodoQuery.SELECT_TODO_LIST_BY_DATE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 일의 투두 목록 조회 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		// 3. 데이터 가공 (ts -> List<Todo>)
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoNo(rs.getInt("todoNo"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			t.setFontColor(rs.getString("fontColor"));
			dayTodoList.add(t);
		}
		
		// 4. 사용 자원 반환
		rs.close();
		stmt.close();
		
		System.out.println("-[Debug] TodoDao.selectTodoListByDate() \"Finished\"");
		return dayTodoList;
	}
	
	/* 투두 삭제 */
	/* 반환 값 : 없음 */
	public void deleteTodo(Connection conn, String memberId) throws SQLException {
		System.out.println("+[Debug] TodoDao.deleteTodo() \"Started\"");
		
		// 1. 투두 삭제하는 쿼리 생성
		String sql = TodoQuery.DELETE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 투두 삭제 쿼리 실행
		int row = stmt.executeUpdate();
		
		// 3. 사용 자원 반환
		stmt.close();
		
		// 4. 결과 디버깅
		// 4-1. 성공
		if(row == 1) {
			System.out.println(" [Debug] \"Successful\" | TodoDao.deleteTodo()");
			System.out.println("-[Debug] TodoDao.deleteTodo() \"Finished\"");
			return;
		}
		// 4-1. 실패
		System.out.println("*[Debug] \"Failed\" | TodoDao.deleteTodo()");
		System.out.println("-[Debug] TodoDao.deleteTodo() \"Finished\"");
		return;
	}
}
