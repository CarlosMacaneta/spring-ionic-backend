package com.webapplication.demo.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 *
 * @author CarlosMacaneta
 */
@Component
public class HeaderExpouserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletResponse hsr = (HttpServletResponse) response;
        
        hsr.addHeader("access-control-expose-headers", "location");
        
        filter.doFilter(request, response);
    }
    
}
