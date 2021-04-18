package org.vulcan.light.simpletomcat.container.session;

import javax.servlet.http.HttpSession;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public interface SessionManager {

    /**
     * 创建session
     *
     * @return
     */
    HttpSession createSession();

    /**
     * 获取session
     *
     * @param id
     *
     * @return
     */
    HttpSession findSession(String id);

    /**
     * 获取全部session
     *
     * @return
     */
    HttpSession[] findSessions();

    /**
     * 添加session
     *
     * @param session
     */
    void add(HttpSession session);

    /**
     * 异常session
     *
     * @param session
     */
    void remove(HttpSession session);

}
