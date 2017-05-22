package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	String charSet;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("CharacterEncodingFilter init() 실행");
		charSet = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		System.out.println("CharacterEncodingFilter doFilter() 실행-전처리");
		request.setCharacterEncoding(charSet);
		//전처리(서블릿이 실행하기전에 처리)
//--------------------------------------------------------------------------
		filterChain.doFilter(request, response);
//--------------------------------------------------------------------------
		System.out.println("CharacterEncodingFilter doFilter() 실행-후처리");
		//후처리(서블릿이 실행한 후에 처리)
	}

	@Override
	public void destroy() {
		
		System.out.println("CharacterEncodingFilter destroy() 실행");
	}

}
