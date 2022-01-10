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

/* admin관련 페이지에서만 요청할 수 있는 필터 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {
	
	/* admin 유효성 검사 */
	// 어드민 로그인 상태에서만 요청할수 있도록 필터링
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("+[Debug] AdminLoginFilter.doFilter() \"Started\"");
		
		System.out.println("LoginFilter.doFilter() 실행");
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("adminId") == null) { // 이미 로그인 되어있는 상태다
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/adminLogin");
			return;
		}
		
		System.out.println("-[Debug] AdminLoginFilter.doFilter() \"Finished\"");
		chain.doFilter(request, response);
	}
}