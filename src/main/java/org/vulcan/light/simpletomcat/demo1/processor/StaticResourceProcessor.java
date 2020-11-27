package org.vulcan.light.simpletomcat.demo1.processor;

import org.vulcan.light.simpletomcat.demo1.request.HttpRequest;
import org.vulcan.light.simpletomcat.demo1.response.HttpResponse;

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
