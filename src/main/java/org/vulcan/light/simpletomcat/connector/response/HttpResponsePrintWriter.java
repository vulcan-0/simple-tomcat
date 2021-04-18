package org.vulcan.light.simpletomcat.connector.response;

import com.sun.tools.javac.util.ByteBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public class HttpResponsePrintWriter extends PrintWriter {

    private String statusLine;

    private String headerLine;

    private ByteBuffer bodyBuffer = new ByteBuffer();

    public HttpResponsePrintWriter(OutputStream out) {
        super(out, true);
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    public void realFlush() {
        try {
            out.write(statusLine);
            out.write(headerLine);
            String blankLine = "\r\n";
            out.write(blankLine);

            String bodyLine = new String(bodyBuffer.elems, 0, bodyBuffer.length);
            out.write(bodyLine);

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void write(int c) {
        bodyBuffer.appendByte(c);
    }

    @Override
    public void write(char buf[], int off, int len) {
        byte[] bytes = new byte[buf.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) buf[i];
        }
        bodyBuffer.appendBytes(bytes, off, len);
    }

    @Override
    public void write(String s, int off, int len) {
        bodyBuffer.appendBytes(s.getBytes(), off, len);
    }

}
