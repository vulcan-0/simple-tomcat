package org.vulcan.light.simpletomcat.common;

import java.io.File;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class Constants {

    public static final int SERVER_PORT = 8080;
    public static final String DEFAULT_ENGINE_NAME = "localhost";
    public static final String DEFAULT_HOST_NAME = "localhost";

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "/webroot";

    public static final String SERVER = "Server";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONNECTION = "Connection";
    public static final String COOKIE = "Cookie";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String JSESSIONID = "JSESSIONID";

    public static final String TEXT_HTML = "text/html";
    public static final String APPLICATION_JSON = "application/json";

    public static final String KEEP_ALIVE = "keep-alive";
    public static final int DEFAULT_KEEP_ALIVE_TIME = 60000;
    public static final int DEFAULT_KEEP_ALIVE_INTERVAL = 20;

    public static final int DEFAULT_CORE_POOL_SIZE = 25;
    public static final int DEFAULT_MAX_POOL_SIZE = 150;
    public static final long DEFAULT_THREAD_KEEP_ALIVE_TIME = 20000;

    public static final int DEFAULT_CORE_CONNECTION_SIZE = 200;
    public static final int DEFAULT_MAX_CONNECTION_SIZE = 1000;

    public static final String SUCCESS_MESSAGE_HEADER = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: %d\r\n" +
            "Connection: keep-alive\r\n" +
            "%s" +
            "\r\n";

    public static final String ERROR_MESSAGE_404 = "HTTP/1.1 404 File Not Found\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: 23\r\n" +
            "Connection: keep-alive\r\n" +
            "%s" +
            "\r\n" +
            "<h1>File Not Found</h1>";

    public static final String ERROR_MESSAGE_500 = "<h1>An error occurred on the server: %s</h1>";

}
