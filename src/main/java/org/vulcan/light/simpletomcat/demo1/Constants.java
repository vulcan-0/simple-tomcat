package org.vulcan.light.simpletomcat.demo1;

import java.io.File;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class Constants {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "/webroot";

    public static final String SUCCESS_MESSAGE_HEADER = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: %d\r\n" +
            "Connection: keep-alive\r\n" +
            "\r\n";

    public static final String ERROR_MESSAGE_404 = "HTTP/1.1 404 File Not Found\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: 23\r\n" +
            "Connection: keep-alive\r\n" +
            "\r\n" +
            "<h1>File Not Found</h1>";

}
