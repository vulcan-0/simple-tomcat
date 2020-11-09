package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.request.Request;
import org.vulcan.light.simpletomcat.demo1.response.Response;

import java.io.IOException;

/**
 * @author luxiaocong
 * @createdOn 2020/11/9
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
