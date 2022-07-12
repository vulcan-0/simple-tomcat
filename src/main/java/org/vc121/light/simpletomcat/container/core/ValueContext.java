package org.vc121.light.simpletomcat.container.core;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public interface ValueContext {

    /**
     * 执行下一个任务
     *
     * @param request
     * @param response
     *
     * @throws IOException
     * @throws ServletException
     */
    void invokeNext(ServletRequest request, ServletResponse response)
            throws IOException, ServletException;

}
