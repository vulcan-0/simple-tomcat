package org.vulcan.light.simpletomcat.demo1.connector.request;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.container.session.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class HttpRequest implements HttpServletRequest {

    private InputStream input;

    private String remoteAddr;
    private String uri;
    private String queryString;
    String contextPath;
    String servletPath;

    private Map<String, List<String>> headers = new LinkedHashMap<String, List<String>>();
    private Map<String, List<String>> attributes = new HashMap<String, List<String>>();
    private Map<String, Cookie> cookieMap = new LinkedHashMap<String, Cookie>();

    private SessionManager sessionManager;
    private HttpSession session;
    private boolean sessionFromUrl;
    private boolean sessionFromCookie;

    public HttpRequest(InputStream input) {
        this.input = input;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @param requestString like: GET /index.html HTTP/1.1
     */
    public void parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(" ");
        if (index1 != -1) {
            index2 = requestString.indexOf(" ", index1 + 1);
            if (index2 > index1) {
                uri = requestString.substring(index1 + 1, index2);
            }
        }

        int ind1 = uri.indexOf("?");
        if (ind1 != -1) {
            queryString = uri.substring(ind1 + 1);
            uri = uri.substring(0, ind1);
        }

        int pos1 = uri.indexOf("/", 1);
        if (pos1 > 0) {
            contextPath = uri.substring(0, pos1);
            servletPath = uri.substring(pos1);
        }
    }

    public void parseSession() {
        String sessionId = null;
        parseQueryString();
        List<String> values = attributes.get(Constants.JSESSIONID);
        if (values != null && !values.isEmpty()) {
            sessionId = values.get(values.size() - 1);
            sessionFromUrl = true;
        }

        if (sessionId == null) {
            sessionFromUrl = false;
            List<String> cookieStrList = headers.get(Constants.COOKIE.toLowerCase());
            if (cookieStrList != null && !cookieStrList.isEmpty()) {
                String cookieStr = cookieStrList.get(cookieStrList.size() - 1);
                parseCookie(cookieStr);
                Cookie cookie = cookieMap.get(Constants.JSESSIONID.toLowerCase());
                if (cookie != null) {
                    sessionId = cookie.getValue();
                    sessionFromCookie = true;
                }
            }
        }

        if (sessionId != null) {
            session = sessionManager.findSession(sessionId);
        }

        if (session == null) {
            sessionFromUrl = false;
            sessionFromCookie = false;
            session = getSession(true);
        }
    }

    private void parseQueryString() {
        if (queryString != null) {
            String[] arr = queryString.split("&");
            for (String str : arr) {
                String[] nameValue = str.trim().split("=");
                if (nameValue.length == 2) {
                    List<String> values = attributes.get(nameValue[0]);
                    if (values == null) {
                        values = new ArrayList<String>();
                        attributes.put(nameValue[0], values);
                    }
                    values.add(nameValue[1]);
                }
            }
        }
    }

    private void parseCookie(String cookieStr) {
        if (cookieStr != null) {
            String[] arr = cookieStr.split(";");
            for (String str : arr) {
                String[] nameValue = str.trim().split("=");
                if (nameValue.length == 2) {
                    Cookie cookie = new Cookie(nameValue[0], nameValue[1]);
                    cookieMap.put(nameValue[0].toLowerCase(), cookie);
                }
            }
        }
    }

    public void setHeader(String name, String value) {
        List<String> values = headers.get(name);
        if (values == null) {
            values = new ArrayList<String>();
            headers.put(name.toLowerCase(), values);
        }
        values.add(value);
    }

    public String getUri() {
        return uri;
    }

    public boolean isAlive() {
        List<String> values = headers.get(Constants.CONNECTION.toLowerCase());
        if (values != null && !values.isEmpty()) {
            if (Constants.KEEP_ALIVE.equals(values.get(values.size() - 1))) {
                return true;
            }
        }
        return false;
    }

    public String getAuthType() {
        return null;
    }

    public Cookie[] getCookies() {
        Cookie[] cookies = new Cookie[cookieMap.size()];
        int i = 0;
        for (String name : cookieMap.keySet()) {
            cookies[i++] = cookieMap.get(name);
        }
        return cookies;
    }

    public long getDateHeader(String name) {
        return 0;
    }

    public String getHeader(String name) {
        List<String> values = headers.get(name);
        if (values != null && !values.isEmpty()) {
            return values.get(values.size() - 1);
        }
        return null;
    }

    public Enumeration<String> getHeaders(String name) {
        if (headers.get(name) != null) {
            return Collections.enumeration(headers.get(name));
        }
        return null;
    }

    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(headers.keySet());
    }

    public int getIntHeader(String name) {
        String value = getHeader(name);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getMethod() {
        return null;
    }

    public String getPathInfo() {
        return null;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getRemoteUser() {
        return null;
    }

    public boolean isUserInRole(String role) {
        return false;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public String getRequestedSessionId() {
        if (session != null) {
            return session.getId();
        }
        return null;
    }

    public String getRequestURI() {
        return uri;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public String getServletPath() {
        return servletPath;
    }

    public HttpSession getSession(boolean create) {
        if (create && session == null) {
            session = sessionManager.createSession();
        }
        return session;
    }

    public HttpSession getSession() {
        return session;
    }

    public String changeSessionId() {
        return null;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return sessionFromCookie;
    }

    public boolean isRequestedSessionIdFromURL() {
        return sessionFromUrl;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return sessionFromUrl;
    }

    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return false;
    }

    public void login(String username, String password) throws ServletException {

    }

    public void logout() throws ServletException {

    }

    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    public Part getPart(String name) throws IOException, ServletException {
        return null;
    }

    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        return null;
    }

    public Object getAttribute(String name) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public long getContentLengthLong() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String name) {
        return null;
    }

    public Enumeration<String> getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String name) {
        return new String[0];
    }

    public Map<String, String[]> getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String name, Object o) {

    }

    public void removeAttribute(String name) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration<Locale> getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    public String getRealPath(String path) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

    public AsyncContext getAsyncContext() {
        return null;
    }

    public DispatcherType getDispatcherType() {
        return null;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

}
