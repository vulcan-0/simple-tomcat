package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.connector.request.HttpRequest;
import org.vulcan.light.simpletomcat.demo1.connector.response.HttpResponse;

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
