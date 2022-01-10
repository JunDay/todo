package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/* 모든 페이지에서 요청할 수 있는 필터 */
@WebFilter("/*") // <- 모든 요청을 가로챈다
public class EncodingFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("+[Debug] EncodingFilter.doFilter() \"Started\"");
		
		/* 로그인 같은 경우 인코딩을 하고서 값을 받아야 하므로 요청 전에 실행해야 함 */
		// 요청 "전" 필터 부분
		request.setCharacterEncoding("utf-8");
		
		// 서블릿보다 필터를 먼저 실행됨
		System.out.println("-[Debug] EncodingFilter.doFilter() \"Finished\"");
		chain.doFilter(request, response); // 사용자 요청한 컨트롤러 메서드, chain <- Contoller의 doPost()
		
		//요청 "후" 필터 부분
	}
}
