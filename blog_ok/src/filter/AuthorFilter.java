package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorFilter",urlPatterns = "*.do")
public class AuthorFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理请求和响应对象的类型装换
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session=request.getSession();
        if(session.getAttribute("loginUser")!=null){
            chain.doFilter(req, resp);
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }

}
