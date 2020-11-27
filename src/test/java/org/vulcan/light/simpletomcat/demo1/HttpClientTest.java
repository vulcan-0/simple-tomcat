package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.common.Constants;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public class HttpClientTest implements Runnable {

    private CountDownLatch latch;

    public HttpClientTest(CountDownLatch latch) {
        this.latch = latch;
    }

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        int n = 10;
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new HttpClientTest(latch));
            thread.start();
        }
        latch.await();
        System.out.println((System.currentTimeMillis() - time) + "ms");
    }

    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", Constants.SERVER_PORT);
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            writer.println("GET /servlet/PrimitiveServlet HTTP/1.1\r\n" +
                    "Host: localhost:8080\r\n" +
                    "Connection: close\r\n");
            writer.flush();

            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(new BufferedInputStream(in));
            CharBuffer charBuffer = CharBuffer.allocate(2048);
            reader.read(charBuffer);

            socket.close();

            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
