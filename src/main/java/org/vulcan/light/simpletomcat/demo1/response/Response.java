package org.vulcan.light.simpletomcat.demo1.response;

import org.vulcan.light.simpletomcat.demo1.Constants;
import org.vulcan.light.simpletomcat.demo1.request.Request;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;

    private OutputStream output;

    private Request request;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
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
            if (file.exists() && file.isFile()) {
                String header = String.format(Constants.SUCCESS_MESSAGE_HEADER, file.length());
                output.write(header.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                output.write(Constants.ERROR_MESSAGE_404.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        PrintWriter writer = new PrintWriter(output, true);
        return writer;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentLengthLong(long len) {

    }

    public void setContentType(String type) {

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
