package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+[Debug] LogoutController.doGet() \"Started\"");
		
		// LoginFilter로 대체됨
		/*
		// 0) 유효성 검사 (해당 로직도 Filter처리 가능)
			// 로그인 정보가 없을 시 
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		// 1) 기존에 있는 세션 해제
		request.getSession().invalidate();
		System.out.println(" [Debug] \"Session remove successful\" | LogoutController.doGet()");
		
		System.out.println("-[Debug] LogoutController.doGet() \"Finished\"");
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

}
