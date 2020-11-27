package org.vulcan.light.simpletomcat.demo1.container;

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
     * 删除阈
     *
     * @param value
     */
    void removeValue(Value value);

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