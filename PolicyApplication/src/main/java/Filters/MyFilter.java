package Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		PrintWriter out = res.getWriter();
		if (req.getParameter("action").equalsIgnoreCase("delete")) {
			if (req.getParameter("userRole").equalsIgnoreCase("admin")) {
				RequestDispatcher rd = req.getRequestDispatcher("policyApp");
				out.println(req.getParameter("id"));
				rd.forward(req, res);
			} else {
				out.println("your not the authenticated user to delete");
			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("policyApp");
			rd.forward(req, res);
		}
	}

	public void destroy() {

	}
}
