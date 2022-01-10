package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.*;

public class MemberDao {
	
	/* 사용자 로그아웃 메서드 */
	/* 반환 값
	 * 	삭제 성공 : 1
	 * 	삭제 실패 : 0 */
	public int deleteMember(Connection conn, String memberId, String memberPw) throws SQLException {
		System.out.println("+[Debug] MemberDao.deleteMember() \"Started\"");
		
		// 1. 멤버를 삭제하는 쿼리 생성
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 멤버 삭제 쿼리 실행
		int row = stmt.executeUpdate();
		
		// 3. 사용 자원 반환
		stmt.close();
		
		// 4. 정상 종료
		if(row == 1) {
			System.out.println(" [Debug] \"Successful\" | MemberDao.deleteMember()");
			System.out.println("-[Debug] MemberDao.deleteMember() \"Finished\"");
			return 1;
		}
		
		// 5. 실패 종료
		System.out.println("*[Debug] \"Failed\" | MemberDao.deleteMember()");
		System.out.println("-[Debug] MemberDao.deleteMember() \"Finished\"");
		return 0;
	}
	
	/* 사용자 로그인 메서드 */
	/* 반환 값 : 로그인된 Member 객체 */
	public Member login(Connection conn, Member paramMember) throws SQLException { 
		System.out.println("+[Debug] MemberDao.login() \"Started\"");
		
		// 1. 반환 Member 객체 생성
		Member loginMember  = null;
		
		// 2. 로그인 멤버 확인 쿼리
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);	//dao는 stmt만 사용하고 conn은 관여하지 않는다.
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 3. 로그인 멤버 확인 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		// 4. 실행된 결과 Member 객체로 가공
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
		}
		System.out.println(" [Debug] loginMember : "+toString());
		
		// 5. 사용 자원 반환
		rs.close();
		stmt.close();
		
		System.out.println("+[Debug] MemberDao.login() \"Finished\"");
		return loginMember;
	}
}