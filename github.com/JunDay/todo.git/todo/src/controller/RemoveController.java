package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.*;
import dao.*;

@WebServlet("/member/remove")
public class RemoveController extends HttpServlet {
	MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] RemoveController.doGet() \"Started\"");
		
		System.out.println("-[Debug] RemoveController.doGet() \"Finished\"");
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] RemoveController.doPost() \"Started\"");
		
		// 1. 로그인된 memberId 가져오기 및 가공
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberId = loginMember.getMemberId();
		String memberPw = request.getParameter("memberPw");
		System.out.println(" ├[value] memberId : "+memberId);
		//System.out.println(" ├[value] memberPw : "+memberPw);
		
		// 2. 멤버 삭제 서비스 실행
		memberService = new MemberService();
		// 2-1. 트랜잭션 성공 시
		try {
			memberService.removeMember(memberId, memberPw);
			System.out.println(" [Debug] \"Successful\" | RemoveController.doPost()");
			request.getSession().invalidate();	// 세션 비활성화
		// 2-2. 트랜잭션 실패 시
		} catch (SQLException e) {
			System.out.println("*[Debug] \"Failed\" | RemoveController.doPost()");
			e.printStackTrace();
		}
		
		System.out.println("-[Debug] RemoveController.doPost() \"Finished\"");
		response.sendRedirect(request.getContextPath()+"/login");
	}
}
