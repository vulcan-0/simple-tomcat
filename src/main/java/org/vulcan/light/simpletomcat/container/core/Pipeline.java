package org.vulcan.light.simpletomcat.container.core;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public interface Pipeline {

    /**
     * 设置基础阈
     *
     * @param value
     */
    void setBasic(Value value);

    /**
     * 获取基础阈
     *
     * @return
     */
    Value getBasic();

    /**
     * 添加阈
     *
     * @param value
     */
    void addValue(Value value);

    /**
     * 移除阈
     *
     * @param value
     */
    void removeValue(Value value);

    /**
     * 获取全部阈
     *
     * @return
     */
    Value[] getValues();

    /**
     * 执行管道任务
     *
     * @param request
     * @param response
     *
     * @throws IOException
     * @throws ServletException
     */
    void invoke(ServletRequest request, ServletResponse response)
            throws IOException, ServletException;

}
