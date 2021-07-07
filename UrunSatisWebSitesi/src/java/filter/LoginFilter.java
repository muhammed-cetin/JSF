package filter;

import entity.Kullanici;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();

        Kullanici user = (Kullanici) req.getSession().getAttribute("kullanici");
        if (user == null) {
            if (url.contains("admin")) {
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if(url.contains("cikisyap")){
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            }else if (url.contains("girisyap") || url.contains("kayitol")) {
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            } else if (url.contains("admin")) {
                if (!user.isAdmin()) {
                    res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            }
            chain.doFilter(request, response);
        }

    }
}
