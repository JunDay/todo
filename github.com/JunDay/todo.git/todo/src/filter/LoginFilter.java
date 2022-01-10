package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* member관련 페이지에서만 요청할 수 있는 필터 */
@WebFilter("/member/*")		// member가 접속하는 모든 페이지에 적용
public class LoginFilter implements Filter {
	
	/* member 유효성 검사 */
	// 멤버 로그인 상태에서만 요청할수 있도록 필터링
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("+[Debug] LoginFilter.doFilter() \"Started\"");
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loginMember") == null) { // 이미 로그인 되어있는 상태다
			((HttpServletResponse)request).sendRedirect(((HttpServletRequest)request).getContextPath()+"/member/calendar");
			return;
		}
		
		System.out.println("-[Debug] LoginFilter.doFilter() \"Finished\"");
		chain.doFilter(request, response);
	}
}
