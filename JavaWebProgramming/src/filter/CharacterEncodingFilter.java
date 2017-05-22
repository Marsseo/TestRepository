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
		
		System.out.println("CharacterEncodingFilter init() ����");
		charSet = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		System.out.println("CharacterEncodingFilter doFilter() ����-��ó��");
		request.setCharacterEncoding(charSet);
		//��ó��(������ �����ϱ����� ó��)
//--------------------------------------------------------------------------
		filterChain.doFilter(request, response);
//--------------------------------------------------------------------------
		System.out.println("CharacterEncodingFilter doFilter() ����-��ó��");
		//��ó��(������ ������ �Ŀ� ó��)
	}

	@Override
	public void destroy() {
		
		System.out.println("CharacterEncodingFilter destroy() ����");
	}

}
