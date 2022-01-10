package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import commons.DBUtil;
import dao.NoticeDao;
import vo.Notice;

public class NoticeService {
	private NoticeDao notcieDao;
	
	/* 공지사항 목록 5개 조회 */
	/* 반환 값 : 5개의 공지사항이 담긴 객체 */
	public List<Notice> getNoticeList5() {
		System.out.println("+[Debug] NoticeService.getNoticeList5() \"Started\"");
		
		List<Notice> list = null;
		Connection conn = null;
		
		// 1. 공지사항 목록 조회 작업 실행
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://15.165.194.216:3306/todo", "root", "java1004");
			notcieDao = new NoticeDao();
			list = notcieDao.selectNoticeList5(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*[Debug] \"Failed\" | NoticeService.getNoticeList5()");
		} finally {
			try {
				conn.close();
				System.out.println(" [Debug] \"Successful\" | NoticeService.getNoticeList5()");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("*[Debug] \"Failed\" | NoticeService.getNoticeList5()");
			}
		}
		
		System.out.println("-[Debug] NoticeService.getNoticeList5() \"Finished\"");
		return list;
	}
}