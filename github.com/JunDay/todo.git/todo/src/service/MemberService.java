package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import vo.*;
import dao.*;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	/* 회원 탈퇴 */
	/* 반환 값
	 * 	삭제 성공 : 1
	 * 	삭제 실패 : 0 */
	public boolean removeMember(String memberId, String memberPw) throws SQLException {
		System.out.println("+[Debug] MemberService.removeMember() \"Started\"");
		
		Connection conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
		
		// 1. 트랜잭션(투두 삭제 -> 멤버 삭제)
		// 1-1. 트랜잭션 성공 시
		try {
			conn.setAutoCommit(false);	// 오토 커밋 X
			todoDao = new TodoDao();
			memberDao = new MemberDao();
			
			// 1-1-1. 투두 삭제 작업 수행
			todoDao.deleteTodo(conn, memberId);
			// 1-1-2. 멤버 삭제 작업 수행
			if(memberDao.deleteMember(conn, memberId, memberPw) != 1) {	// 성공 : 1, 실패 : 0 반환됨
				System.out.println("*[Debug] \"Failed\" | MemberService.removeMember()");
				throw new Exception();
			}
			conn.commit();
			System.out.println(" [Debug] \"Successful\" | MemberDao.deleteMember()");
			
		// 1-2. 트랜잭션 실패 시
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("-[Debug] MemberService.removeMember() \"Finished\"");
		return true;
	}
	
	/* 회원 로그인 */
	/* 반환 값 : 로그인된 member 객체 */
	public Member login(Member member) {
		System.out.println("+[Debug] MemberService.login() \"Started\"");
		
		// 0. 로그인에 필요한 Member, Connection 객체 생성
		Member loginMember = null;
		Connection conn = null;
		
		// 1. 로그인 작업 실행
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
			this.memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch(Exception e) {
			System.out.println("*[Debug] \"Failed\" | MemberService.login()");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println(" [Debug] \"Successful\" | MemberService.login()");
			} catch(SQLException e) {
				System.out.println("*[Debug] \"Failed\" | MemberService.login()");
				e.printStackTrace();
			}
		}
		
		System.out.println("-[Debug] MemberService.login() \"Finished\"");
		return loginMember;
	}
}
