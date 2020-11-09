package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.request.Request;
import org.vulcan.light.simpletomcat.demo1.request.RequestFacade;
import org.vulcan.light.simpletomcat.demo1.response.Response;
import org.vulcan.light.simpletomcat.demo1.response.ResponseFacade;

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

    public void process(Request request, Response response) {
        String uri = request.getUri();
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

            try {
                PrintWriter out = response.getWriter();
                out.println(Constants.ERROR_MESSAGE_404);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        Servlet servlet;
        try {
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
