package org.vulcan.light.simpletomcat.connector;

import org.vulcan.light.simpletomcat.common.Constants;
import org.vulcan.light.simpletomcat.common.Logger;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public class TcpConnectionPool {

    private static Logger logger = new Logger(TcpConnectionPool.class);

    private static volatile int count;

    private TcpConnectionPool() {

    }

    private static Queue<TcpConnection> queue = new LinkedBlockingQueue<TcpConnection>();

    public static synchronized TcpConnection get() {
        TcpConnection tcpConnection = queue.poll();
        if (tcpConnection == null) {
            if (count < Constants.DEFAULT_MAX_CONNECTION_SIZE) {
                tcpConnection = new TcpConnection();
                count++;
                logger.info("Pool Size: " + count);
            } else {
                throw new RuntimeException("Too many connection!");
            }
        }
        return tcpConnection;
    }

    public static synchronized void recycle(TcpConnection tcpConnection) {
        if (count < Constants.DEFAULT_CORE_CONNECTION_SIZE) {
            queue.add(tcpConnection);
        } else {
            count--;
        }
    }

}
