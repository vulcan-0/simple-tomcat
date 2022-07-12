package org.vc121.light.simpletomcat.container.filter;

import org.vc121.light.simpletomcat.common.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/12/3
 */
public class SimpleFilter implements Filter {

    private Logger logger = new Logger(this.getClass());

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("doFilter");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}
