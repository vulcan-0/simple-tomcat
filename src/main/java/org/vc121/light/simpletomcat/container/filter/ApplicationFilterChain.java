package org.vc121.light.simpletomcat.container.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/12/3
 */
public class ApplicationFilterChain implements FilterChain {

    private List<ApplicationFilterConfig> filterConfigs;
    private int pos;
    private Servlet servlet;

    public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (pos < filterConfigs.size()) {
            ApplicationFilterConfig filterConfig = filterConfigs.get(pos++);
            try {
                Filter filter = filterConfig.getFilter();
                filter.doFilter(request, response, this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            servlet.service(request, response);
        }
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public void setFilterConfigs(List<ApplicationFilterConfig> filterConfigs) {
        this.filterConfigs = filterConfigs;
    }

}
