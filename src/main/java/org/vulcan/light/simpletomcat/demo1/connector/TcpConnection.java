package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.Logger;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequest;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponse;
import org.vulcan.light.simpletomcat.demo1.container.Contained;
import org.vulcan.light.simpletomcat.demo1.container.Container;

import java.io.*;
import java.net.Socket;

/**
 * @author luxiaocong
 * @createdOn 2020/11/10
 */
public class TcpConnection implements Contained, Runnable {

    private Logger logger = new Logger(this.getClass());

    private HttpRequest request;
    private HttpResponse response;

    private Container container;
    private Socket socket;
    private boolean keepIt;
    private boolean keepAlive;
    private int keepAliveTime;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            do {
                Thread.sleep(Constants.DEFAULT_KEEP_ALIVE_INTERVAL);
                keepAliveTime -= Constants.DEFAULT_KEEP_ALIVE_INTERVAL;

                process();

                boolean requestAlive = request != null ? request.isAlive() : false;
                keepAlive = (keepIt || requestAlive) && keepAliveTime > 0;
            } while (keepAlive);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            release();
        }
    }

    public void process() throws IOException {
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        if (reader.ready()) {
            keepIt = false;
            keepAliveTime = Constants.DEFAULT_KEEP_ALIVE_TIME;

            request = new HttpRequest(input);
            parseRequest(reader, output);
            parseHeaders(reader);

            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader(Constants.SERVER, "Simple Servlet Container");

            if (request.getContextPath() != null) {
                ServletProcessor servletProcessor = new ServletProcessor();
                servletProcessor.setContainer(container);
                servletProcessor.process(request, response);
            } else {
                StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                staticResourceProcessor.process(request, response);
            }
        } else {
            keepIt = keepAlive;
        }
    }

    private void parseRequest(BufferedReader reader, OutputStream output) throws IOException {
        String requestString = reader.readLine();
        logger.console(requestString, Logger.ANSI_YELLOW);
        request.parseUri(requestString);
    }

    private void parseHeaders(BufferedReader reader) throws IOException {
        logger.console("#### Request Headers Start ###########################################", Logger.ANSI_YELLOW);
        while (reader.ready()) {
            String line = reader.readLine();
            if (line != null && !line.equals("")) {
                String[] arr = line.split(":");
                if (arr.length == 2) {
                    request.setHeader(arr[0].trim(), arr[1].trim());
                    logger.console(line, Logger.ANSI_YELLOW);
                }
            }
        }
        logger.console("#### Request Headers End #############################################", Logger.ANSI_YELLOW);
    }

    private void release() {
        try {
            socket.close();
            socket = null;
            request = null;
            response = null;
            keepIt = false;
            keepAlive = false;
            keepAliveTime = Constants.DEFAULT_KEEP_ALIVE_TIME;
        } catch (IOException e) {
            e.printStackTrace();
        }

        TcpConnectionPool.recycle(this);
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
