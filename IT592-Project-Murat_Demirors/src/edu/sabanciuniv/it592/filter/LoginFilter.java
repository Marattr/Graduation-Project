package edu.sabanciuniv.it592.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.sabanciuniv.it592.mbeans.LoginBean;




@WebFilter("/login/*")
public class LoginFilter implements Filter {
	
	@Inject LoginBean loginBean;
  
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
			
		String contextPath=req.getContextPath();
		if(loginBean.isLoggedin()) {
			chain.doFilter(request, response);
		}else {			
			resp.sendRedirect(contextPath+"/login.xhtml");
		}
		
	}

	

}
