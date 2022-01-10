package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Notice;

public class NoticeDao {
	
	/* 공지사항 5개 출력 */
	/* 반환 값 : 5개의 공지사항이 담긴 리스트 객체 */
	public List<Notice> selectNoticeList5(Connection conn) throws SQLException {
		System.out.println("+[Debug] NoticeDao.selectNoticeList5() \"Started\"");
		
		// 0. 5개의 공지사항을 담기 위한 객체 생성
		List<Notice> list = new ArrayList<>();
		
		// 1. 공직사항 5개를 조회하는 쿼리 생성
		PreparedStatement stmt = conn.prepareStatement(NoticeQuery.SELECT_NOTICE_LIST5);
		System.out.println(" ├[value] stmt : "+stmt);
		
		// 2. 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		// 3. 데이터 가공 (rs -> List<Notice>)
		while(rs.next()) {
			Notice notice = new Notice();
			notice.setNoticeNo(rs.getInt("noticeNo"));
			notice.setNoticeTitle(rs.getString("noticeTitle"));
			notice.setCreateDate(rs.getString("createDate"));
			list.add(notice);
		}
		
		// 4. 사용 자원 반환
		rs.close();
		stmt.close();
		
		System.out.println("-[Debug] NoticeDao.selectNoticeList5() \"Finished\"");
		return list;
	}
}
