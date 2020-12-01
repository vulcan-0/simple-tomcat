package org.vulcan.light.simpletomcat.demo1.container.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luxiaocong
 * @createdOn 2020/11/30
 */
public class StandardSession implements HttpSession {

    private String id;
    private long creationTime;
    private long lastAccessedTime;
    private int maxInactiveInterval = 60000;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public StandardSession(String id) {
        this(id, System.currentTimeMillis(), System.currentTimeMillis());
    }

    public StandardSession(String id, long creationTime, long lastAccessedTime) {
        this.id = id;
        this.creationTime = creationTime;
        this.lastAccessedTime = lastAccessedTime;
    }

    public String getId() {
        return this.id;
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public long getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public Object getValue(String name) {
        return attributes.get(name);
    }

    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(attributes.keySet());
    }

    public String[] getValueNames() {
        return attributes.keySet().toArray(new String[attributes.size()]);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void putValue(String name, Object value) {
        attributes.put(name, value);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public void removeValue(String name) {
        attributes.remove(name);
    }

    public void invalidate() {

    }

    public boolean isNew() {
        return false;
    }

}
