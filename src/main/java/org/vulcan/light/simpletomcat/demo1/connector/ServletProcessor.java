package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.HttpStatus;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequest;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequestFacade;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponse;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponseFacade;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getUri();

        Class myClass = loadServlet(uri);
        if (myClass != null) {
            try {
                Servlet servlet = (Servlet) myClass.newInstance();
                service(servlet, request, response);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            sendNotFound(response);
        }
    }

    private Class loadServlet(String uri) {
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null,
                    classPath.getCanonicalPath() + File.separator)).toExternalForm();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            servletName = "org.vulcan.light.simpletomcat.demo1.servlet." + servletName;
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myClass;
    }

    private void sendNotFound(HttpResponse response) {
        try {
            response.setStatus(HttpStatus.STATUS_404.getSc());
            response.setContentType(Constants.TEXT_HTML);
            response.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);

            PrintWriter out = response.getWriter();
            String body = "<h1>File Not Found</h1>";
            out.write(body);
            response.setContentLength(body.length());
            response.finishResponse();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void service(Servlet servlet, HttpRequest request, HttpResponse response) {
        try {
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);

            response.setStatus(HttpStatus.STATUS_200.getSc());
            response.setContentType(Constants.TEXT_HTML);
            response.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);

            servlet.service(requestFacade, responseFacade);
            if (response.getContentLength() == 0) {
                response.setContentLengthLong(response.getBodyLength());
            }
            response.finishResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
