package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.Logger;
import org.vulcan.light.simpletomcat.demo1.container.core.Contained;
import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.session.SessionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/10
 */
public class HttpConnector implements Contained, Runnable {

    private Logger logger = new Logger(this.getClass());

    private Container container;

    private boolean stopped;

    private String scheme = "http";

    private SessionManager sessionManager;

    public String getScheme() {
        return scheme;
    }

    private Executor executor = new ThreadPoolExecutor(Constants.DEFAULT_CORE_POOL_SIZE,
            Constants.DEFAULT_MAX_POOL_SIZE,
            Constants.DEFAULT_THREAD_KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Constants.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped) {
            try {
                Socket socket = serverSocket.accept();
                logger.info("Receive socket: " + socket);
                TcpConnection connection = TcpConnectionPool.get();
                connection.setContainer(container);
                connection.setSocket(socket);
                connection.setSessionManager(sessionManager);
                executor.execute(connection);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        container.start();
        Thread thread = new Thread(this);
        thread.start();
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

}
