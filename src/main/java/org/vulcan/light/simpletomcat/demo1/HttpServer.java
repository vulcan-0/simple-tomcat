package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.connector.HttpConnector;

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

        HttpConnector connector = new HttpConnector();
        connector.start();
    }

}
