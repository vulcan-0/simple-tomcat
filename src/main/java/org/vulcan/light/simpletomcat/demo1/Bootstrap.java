package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.connector.HttpConnector;
import org.vulcan.light.simpletomcat.demo1.container.core.*;
import org.vulcan.light.simpletomcat.demo1.container.filter.ApplicationFilterConfig;
import org.vulcan.light.simpletomcat.demo1.container.filter.FilterDef;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleContextLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleEngineLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleHostLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.SimpleWrapperLifecycleListener;
import org.vulcan.light.simpletomcat.demo1.container.session.StandardSessionManager;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardContext;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardEngine;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardHost;
import org.vulcan.light.simpletomcat.demo1.container.standard.StandardWrapper;
import org.vulcan.light.simpletomcat.demo1.container.value.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public class Bootstrap {

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
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterName("SimpleFilter");
        filterDef.setFilterClass("org.vulcan.light.simpletomcat.demo1.container.filter.SimpleFilter");
        ApplicationFilterConfig filterConfig = new ApplicationFilterConfig(context, filterDef);
        context.addFilterConfig(filterConfig);

        Host host = new StandardHost();
        host.setName(Constants.DEFAULT_HOST_NAME);
        host.addChild(context);
        host.addLifecycleListener(new SimpleHostLifecycleListener());

        StandardEngine engine = new StandardEngine();
        engine.setName(Constants.DEFAULT_ENGINE_NAME);
        engine.addChild(host);
        engine.addLifecycleListener(new SimpleEngineLifecycleListener());
        engine.addValue(new ClientIpLoggerValue());
        engine.addValue(new CookieValue());
        engine.addValue(new SessionValue());
        engine.addValue(new HeaderLoggerValue());
        engine.addValue(new ServerInternalErrorValue());

        HttpConnector connector = new HttpConnector();
        connector.setContainer(engine);
        connector.setSessionManager(new StandardSessionManager());

        Service service = new StandardService();
        service.setContainer(engine);
        service.addConnector(connector);

        Server server = new StandardServer();
        server.addService(service);
        server.start();
    }

}
