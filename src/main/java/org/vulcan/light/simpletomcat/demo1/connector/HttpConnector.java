package org.vulcan.light.simpletomcat.demo1.connector;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.Logger;
import org.vulcan.light.simpletomcat.demo1.processor.TcpConnection;
import org.vulcan.light.simpletomcat.demo1.processor.TcpConnectionPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author luxiaocong
 * @createdOn 2020/11/10
 */
public class HttpConnector implements Runnable {

    private Logger logger = new Logger(this.getClass());

    private boolean stopped;

    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    private Executor executor = new ThreadPoolExecutor(Constants.CORE_POOL_SIZE,
            Constants.MAXIMUM_POOL_SIZE,
            Constants.KEEP_ALIVE_TIME,
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
                connection.setSocket(socket);
                executor.execute(connection);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

}
