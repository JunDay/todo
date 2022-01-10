package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.*;
import dao.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private MemberService memberService;
	private NoticeService noticeService;
	
	/* login 페이지 폼 출력 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] LoginController.doGet() \"Started\"");
		
		// 0. 유효성 검사 (해당 로직도 Filter처리 가능)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// 1. 공지사항 목록 조회 메서드 실행
		noticeService = new NoticeService();
		List<Notice> noticeList = noticeService.getNoticeList5();
		request.setAttribute("noticeList", noticeList);
		System.out.println(" ├[value] noticeList : "+noticeList.toString());
		
		System.out.println("-[Debug] LoginController.doGet() \"Finished\"");
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	/* login 페이지 액션 출력 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8"); // doPost() 상단에 무조건 있어야 하는 코드
		System.out.println("+[Debug] LoginController.doPost() \"Started\"");
		
		// 0. 유효성 검사 (해당 로직도 Filter처리 가능)
			// 로그인 정보가 있을 시
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// 1. 로인정보 가져오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(" ├[value] memberId : "+memberId);
		System.out.println(" ├[value] memberPw : "+memberPw);
		
		// 2. 데이터 가공 (String -> Member)
		Member paraMember = new Member();
		paraMember.setMemberId(memberId);
		paraMember.setMemberPw(memberPw);
		
		// 3. 회원 로그인 메서드 실행
		memberService = new MemberService();
		Member loginMember = memberService.login(paraMember);
		
		// 4. 로그인 실패시
		if(loginMember == null) {
			System.out.println("=[Debug] \"Session creation failed\" | LoginController.selvet.doPost()");
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 5. 로그인 성공시, 멤버 세션 생성
		session.setAttribute("loginMember", loginMember);
		System.out.println(" ├[value] loginMember : "+session.getId());
		
		System.out.println("-[Debug] LoginController.doPost() \"Finished\"");
		response.sendRedirect(request.getContextPath()+"/member/calendar");
	}
}
