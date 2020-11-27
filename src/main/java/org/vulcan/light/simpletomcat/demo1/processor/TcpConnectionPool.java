package org.vulcan.light.simpletomcat.demo1.processor;

import org.vulcan.light.simpletomcat.demo1.common.Constants;
import org.vulcan.light.simpletomcat.demo1.common.Logger;

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
            if (count < Constants.MAX_PROCESSOR_SIZE) {
                tcpConnection = new TcpConnection();
                count++;
                logger.info("Pool Size: " + count);
            } else {
                throw new RuntimeException("Too many processor!");
            }
        }
        return tcpConnection;
    }

    public static synchronized void recycle(TcpConnection tcpConnection) {
        if (count < Constants.CORE_PROCESSOR_SIZE) {
            queue.add(tcpConnection);
        } else {
            count--;
        }
    }

}
