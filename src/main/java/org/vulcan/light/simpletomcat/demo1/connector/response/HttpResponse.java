package org.vulcan.light.simpletomcat.demo1.connector.response;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.HttpStatus;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class HttpResponse implements HttpServletResponse {

    private static final int BUFFER_SIZE = 1024;

    private OutputStream output;

    private HttpResponsePrintWriter writer;

    private HttpRequest request;

    private int status;

    private Map<String, String> headers = new LinkedHashMap<String, String>();

    public HttpResponse(OutputStream output) {
        this.output = output;
        writer = new HttpResponsePrintWriter(output);
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            if (request.getUri() == null) {
                return;
            }

            File file = new File(Constants.WEB_ROOT, request.getUri());
            String headerStr = getHeaderString();
            if (file.exists() && file.isFile()) {
                String header = String.format(Constants.SUCCESS_MESSAGE_HEADER, file.length(), headerStr);
                output.write(header.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String message = String.format(Constants.ERROR_MESSAGE_404, headerStr);
                output.write(message.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    private String getHeaderString() {
        StringBuffer headerBuffer = new StringBuffer();
        for (String name : headers.keySet()) {
            String value = headers.get(name);
            headerBuffer.append(name).append(": ").append(value).append("\r\n");
        }
        return headerBuffer.toString();
    }

    public long getBodyLength() {
        return writer.getBodyLength();
    }

    public void finishResponse() {
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        String statusLine = String.format("HTTP/1.1 %d %s\r\n", httpStatus.getSc(), httpStatus.getSm());
        writer.setStatusLine(statusLine);

        StringBuffer headerBuffer = new StringBuffer();
        for (String name : headers.keySet()) {
            String value = headers.get(name);
            String str = String.format("%s: %s\r\n", name, value);
            headerBuffer.append(str);
        }
        writer.setHeaderLine(headerBuffer.toString());

        writer.realFlush();
    }

    public void addCookie(Cookie cookie) {

    }

    public boolean containsHeader(String name) {
        return false;
    }

    public String encodeURL(String url) {
        return null;
    }

    public String encodeRedirectURL(String url) {
        return null;
    }

    public String encodeUrl(String url) {
        return null;
    }

    public String encodeRedirectUrl(String url) {
        return null;
    }

    public void sendError(int sc, String msg) throws IOException {

    }

    public void sendError(int sc) throws IOException {

    }

    public void sendRedirect(String location) throws IOException {

    }

    public void setDateHeader(String name, long date) {

    }

    public void addDateHeader(String name, long date) {

    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public void addHeader(String name, String value) {

    }

    public void setIntHeader(String name, int value) {

    }

    public void addIntHeader(String name, int value) {

    }

    public void setStatus(int sc) {
        status = sc;
    }

    public void setStatus(int sc, String sm) {

    }

    public int getStatus() {
        return status;
    }

    public String getHeader(String name) {
        return null;
    }

    public Collection<String> getHeaders(String name) {
        return null;
    }

    public Collection<String> getHeaderNames() {
        return null;
    }

    public String getCharacterEncoding() {
        String type = getContentType();
        String[] arr = type.split(";");
        if (arr.length > 1) {
            return arr[1];
        }
        return null;
    }

    public String getContentType() {
        return headers.get(Constants.CONTENT_TYPE);
    }

    public long getContentLength() {
        if (headers.get(Constants.CONTENT_LENGTH) != null) {
            return Long.valueOf(headers.get(Constants.CONTENT_LENGTH));
        }
        return 0;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    public void setCharacterEncoding(String charset) {
        String type = getContentType();
        String[] arr = type.split(";");
        type = String.format("%s; %s", arr[0], charset);
        setContentType(type);
    }

    public void setContentLength(int len) {
        headers.put(Constants.CONTENT_LENGTH, "" + len);
    }

    public void setContentLengthLong(long len) {
        headers.put(Constants.CONTENT_LENGTH, "" + len);
    }

    public void setContentType(String type) {
        headers.put(Constants.CONTENT_TYPE, type);
    }

    public void setBufferSize(int size) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }

}
