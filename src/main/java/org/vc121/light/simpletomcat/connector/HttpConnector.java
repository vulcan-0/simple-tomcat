package org.vc121.light.simpletomcat.connector;

import org.vc121.light.simpletomcat.common.Constants;
import org.vc121.light.simpletomcat.container.core.Contained;
import org.vc121.light.simpletomcat.container.core.Container;
import org.vc121.light.simpletomcat.container.lifecycle.Lifecycle;
import org.vc121.light.simpletomcat.container.lifecycle.LifecycleListener;
import org.vc121.light.simpletomcat.common.Logger;
import org.vc121.light.simpletomcat.container.session.SessionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/10
 */
public class HttpConnector implements Connector, Contained, Runnable, Lifecycle {

    private Logger logger = new Logger(this.getClass());

    private int port = Constants.SERVER_PORT;

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
            serverSocket = new ServerSocket(port);
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
        if (sessionManager != null && sessionManager instanceof Lifecycle) {
            ((Lifecycle) sessionManager).start();
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (sessionManager != null && sessionManager instanceof Lifecycle) {
            ((Lifecycle) sessionManager).stop();
        }
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

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

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

}
