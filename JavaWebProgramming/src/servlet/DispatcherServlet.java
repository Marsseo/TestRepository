package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() 실행"); 
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("ServletConfig() 실행");
		String name1 = config.getInitParameter("name1");
		String name2 = config.getInitParameter("name2");
		System.out.println(name1);
		System.out.println(name2);
	}
}
