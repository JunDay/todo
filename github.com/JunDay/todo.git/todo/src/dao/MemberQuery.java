package dao;

public class MemberQuery {
	// DAO에서 쿼리를 분리할 수도 있다.
	// 쿼리 관리하기 편하지만 읽기 힘듦
	public static final String LOGIN;
	public static final String DELETE_MEMBER;
	// 스태틱 필드, 초기화
	static {
		LOGIN = "SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw = ?";
		DELETE_MEMBER = "DELETE FROM member WHERE member_id=? AND member_pw=?";
	}
}
