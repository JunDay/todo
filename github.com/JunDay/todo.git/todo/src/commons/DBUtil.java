package commons;

import java.sql.*;


public class DBUtil {
	/* DB 연결 메서드
	 * 여러 연결들이 있는 경우를 생각해, 매개변수로 연결정보를 받는다. */
	public static Connection getConnection(String url, String user, String password) {
		System.out.println("+[Debug] DBUtil.getConnection() \"Started\"");
		
		// 1) 반환해줄 Connection 객체 생성
		Connection conn = null;
		
		// 2) DB 연결
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(" [Debug] \"Session creation successful\" | DBUtil.getConnection()");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-[Debug] \"Session creation failed\" | DBUtil.getConnection()");
		}
		
		// 3) DB 연결 정보 반환
		return conn;
	}
}
