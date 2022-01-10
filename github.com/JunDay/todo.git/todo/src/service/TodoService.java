package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import commons.*;
import dao.*;
import vo.*;

public class TodoService {
	private TodoDao todoDao;	
	
	/* 투두 삽입 */
	/* 반환 값 : 없음 */
	public void insertTodo(Todo todo) {
		System.out.println("+[Debug] TodoService.insertTodo() \"Started\"");
		
		Connection conn = null;
		
		// 1. 투두 삽입 작업 수행
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.insertTodo(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("*[Debug] \"Failed\" | TodoService.insertTodo()");
		} finally {
			try {
				conn.close();
				System.out.println(" [Debug] \"Successful\" | TodoService.insertTodo()");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("*[Debug] \"Failed\" | TodoService.insertTodo()");
			}
		}
		
		System.out.println("-[Debug] TodoService.insertTodo() \"Finished\"");
	}
	
	/* 투두 리스트 조회 */
	/* 반환 값 : 일의 투두 목록이 담긴 객체 */
	public List<Todo> getTodoListByDate(Todo todo){
		System.out.println("+[Debug] TodoService.getTodoListByDate() \"Started\"");
		
		List<Todo> list = new ArrayList<Todo>();		
		Connection conn = null;
		
		// 1. 일에 해당되는 투두들의 목록 조회 작업 수행
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			list = todoDao.selectTodoListByDate(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("*[Debug] \"Failed\" | TodoService.getTodoListByDate()");
		} finally {
			try {
				conn.close();
				System.out.println(" [Debug] \"Successful\" | TodoService.getTodoListByDate()");
			} catch(SQLException e1) {
				e1.printStackTrace();
				System.out.println("*[Debug] \"Failed\" | TodoService.getTodoListByDate()");
			}
		}
		
		System.out.println("-[Debug] TodoService.getTodoListByDate() \"Finished\"");
		return list;
	}
}
