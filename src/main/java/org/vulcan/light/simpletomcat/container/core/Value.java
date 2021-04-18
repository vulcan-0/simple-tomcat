package org.vulcan.light.simpletomcat.container.core;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public interface Value {

    /**
     * 执行任务
     *
     * @param request
     * @param response
     * @param context
     *
     * @throws IOException
     * @throws ServletException
     */
    void invoke(ServletRequest request, ServletResponse response, ValueContext context)
            throws IOException, ServletException;

}
