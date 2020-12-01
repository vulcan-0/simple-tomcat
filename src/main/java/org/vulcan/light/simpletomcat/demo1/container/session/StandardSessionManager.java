package org.vulcan.light.simpletomcat.demo1.container.session;

import org.vulcan.light.simpletomcat.demo1.common.Logger;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.Lifecycle;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.LifecycleListener;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class StandardSessionManager implements SessionManager, Lifecycle {

    private Logger logger = new Logger(this.getClass());

    private Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

    private boolean stopped;

    public HttpSession createSession() {
        HttpSession session = new StandardSession(UUID.randomUUID().toString());
        sessionMap.put(session.getId(), session);
        return session;
    }

    public HttpSession findSession(String id) {
        HttpSession session = sessionMap.get(id);
        if (session instanceof StandardSession) {
            ((StandardSession) session).setLastAccessedTime(System.currentTimeMillis());
        }
        return session;
    }

    public HttpSession[] findSessions() {
        HttpSession[] sessions = new HttpSession[sessionMap.size()];
        int i = 0;
        for (String id : sessionMap.keySet()) {
            sessions[i++] = sessionMap.get(id);
        }
        return sessions;
    }

    public void add(HttpSession session) {
        sessionMap.put(session.getId(), session);
    }

    public void remove(HttpSession session) {
        sessionMap.remove(session.getId());
    }

    public void start() {
        Thread thread = new Background();
        thread.start();
    }

    public void stop() {
        stopped = true;
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

    }

    class Background extends Thread {

        @Override
        public void run() {
            while (!stopped) {
                if (!sessionMap.isEmpty()) {
                    for (String id : sessionMap.keySet()) {
                        HttpSession session = sessionMap.get(id);
                        if (session != null) {
                            long now = System.currentTimeMillis();
                            if (now - session.getLastAccessedTime() > session.getMaxInactiveInterval()) {
                                sessionMap.remove(session.getId());
                                logger.debug("Session[" + session.getId() + "] expired!");
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
