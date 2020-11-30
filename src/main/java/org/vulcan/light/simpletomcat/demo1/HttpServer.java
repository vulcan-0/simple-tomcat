package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.connector.HttpConnector;
import org.vulcan.light.simpletomcat.demo1.container.core.*;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleContextLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleEngineLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleHostLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleWrapperLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardContext;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardEngine;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardHost;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardWrapper;
import org.vulcan.light.simpletomcat.demo1.container.value.ClientIpLoggerValue;
import org.vulcan.light.simpletomcat.demo1.container.value.HeaderLoggerValue;
import org.vulcan.light.simpletomcat.demo1.container.value.ServerInternalErrorValue;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class HttpServer {

    private static String simpleServletContainer = "\n" +
            "   _____ _                 __        _____                 __     __     ______            __        _                \n" +
            "  / ___/(_____ ___  ____  / ___     / ___/___  ______   __/ ___  / /_   / ________  ____  / /_____ _(_____  ___  _____\n" +
            "  \\__ \\/ / __ `__ \\/ __ \\/ / _ \\    \\__ \\/ _ \\/ ___| | / / / _ \\/ __/  / /   / __ \\/ __ \\/ __/ __ `/ / __ \\/ _ \\/ ___/\n" +
            " ___/ / / / / / / / /_/ / /  __/   ___/ /  __/ /   | |/ / /  __/ /_   / /___/ /_/ / / / / /_/ /_/ / / / / /  __/ /    \n" +
            "/____/_/_/ /_/ /_/ .___/_/\\___/   /____/\\___/_/    |___/_/\\___/\\__/   \\____/\\____/_/ /_/\\__/\\__,_/_/_/ /_/\\___/_/     \n" +
            "                /_/                                                                                                   " +
            "\n";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        System.out.println(simpleServletContainer);

        Wrapper wrapper = new StandardWrapper();
        wrapper.setName("/PrimitiveServlet");
        wrapper.addLifecycleListener(new SimpleWrapperLifecycleListener());

        Context context = new StandardContext();
        context.setName("/servlet");
        context.addChild(wrapper);
        context.addLifecycleListener(new SimpleContextLifecycleListener());

        Host host = new StandardHost();
        host.setName(Constants.DEFAULT_HOST_NAME);
        host.addChild(context);
        host.addLifecycleListener(new SimpleHostLifecycleListener());

        Engine engine = new StandardEngine();
        engine.setName(Constants.DEFAULT_ENGINE_NAME);
        engine.addChild(host);
        engine.addLifecycleListener(new SimpleEngineLifecycleListener());

        ClientIpLoggerValue clientIpLoggerValue = new ClientIpLoggerValue();
        ((AbstractContainerBase) engine).addValue(clientIpLoggerValue);
        HeaderLoggerValue headerLoggerValue = new HeaderLoggerValue();
        ((AbstractContainerBase) engine).addValue(headerLoggerValue);
        ServerInternalErrorValue serverInternalErrorValue = new ServerInternalErrorValue();
        ((AbstractContainerBase) engine).addValue(serverInternalErrorValue);

        HttpConnector connector = new HttpConnector();
        connector.setContainer(engine);
        connector.start();
    }

}
