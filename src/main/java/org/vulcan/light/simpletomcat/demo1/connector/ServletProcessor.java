package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.HttpStatus;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequest;
import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequestFacade;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponse;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponseFacade;
import org.vulcan.light.simpletomcat.demo1.container.Contained;
import org.vulcan.light.simpletomcat.demo1.container.Container;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class ServletProcessor implements Contained {

    private Container container;

    public void process(HttpRequest request, HttpResponse response) throws IOException {
        try {
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);

            response.setStatus(HttpStatus.STATUS_200.getSc());
            response.setContentType(Constants.TEXT_HTML);
            response.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);

            container.invoke(requestFacade, responseFacade);
            if (response.getContentLength() == 0) {
                response.setContentLengthLong(response.getBodyLength());
            }
            response.finishResponse();
        } catch (ServletException e) {
            e.printStackTrace();

            response.setStatus(HttpStatus.STATUS_500.getSc());
            response.setContentType(Constants.TEXT_HTML);
            response.setHeader(Constants.CONNECTION, Constants.KEEP_ALIVE);
            String message = String.format(Constants.ERROR_MESSAGE_500, e.getLocalizedMessage());
            response.getWriter().write(message);
            response.setContentLengthLong(response.getBodyLength());
            response.finishResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
