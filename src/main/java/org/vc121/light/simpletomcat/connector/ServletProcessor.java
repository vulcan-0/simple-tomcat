package org.vc121.light.simpletomcat.connector;

import org.vc121.light.simpletomcat.container.core.Contained;
import org.vc121.light.simpletomcat.container.core.Container;
import org.vc121.light.simpletomcat.common.Constants;
import org.vc121.light.simpletomcat.common.HttpStatus;
import org.vc121.light.simpletomcat.connector.request.HttpRequest;
import org.vc121.light.simpletomcat.connector.request.HttpRequestFacade;
import org.vc121.light.simpletomcat.connector.response.HttpResponse;
import org.vc121.light.simpletomcat.connector.response.HttpResponseFacade;

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
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.finishResponse();
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

}
