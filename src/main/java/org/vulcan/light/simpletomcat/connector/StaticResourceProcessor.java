package org.vulcan.light.simpletomcat.connector;

import org.vulcan.light.simpletomcat.connector.request.HttpRequest;
import org.vulcan.light.simpletomcat.connector.response.HttpResponse;

import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
