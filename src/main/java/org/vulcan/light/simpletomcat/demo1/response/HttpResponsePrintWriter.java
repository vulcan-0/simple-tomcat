package org.vulcan.light.simpletomcat.demo1.response;

import com.sun.tools.javac.util.ByteBuffer;
import org.vulcan.light.simpletomcat.demo1.common.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public class HttpResponsePrintWriter extends PrintWriter {

    private Logger logger = new Logger(this.getClass());

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

    public long getBodyLength() {
        return bodyBuffer.length;
    }

    public void realFlush() {
        try {
            logger.console("#### Response Start ##################################################", Logger.ANSI_PURPLE);
            out.write(statusLine);
            logger.console("response statusLine\n" + statusLine, Logger.ANSI_PURPLE);

            out.write(headerLine);
            logger.console("response headers\n" + headerLine, Logger.ANSI_PURPLE);

            String blankLine = "\r\n";
            out.write(blankLine);

            String bodyLine = new String(bodyBuffer.elems, 0, bodyBuffer.length);
            out.write(bodyLine);
            logger.console("response body\n" + bodyLine, Logger.ANSI_PURPLE);
            out.flush();
            logger.console("#### Response End ####################################################", Logger.ANSI_PURPLE);
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
