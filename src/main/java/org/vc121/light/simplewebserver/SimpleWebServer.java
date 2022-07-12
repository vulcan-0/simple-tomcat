package org.vc121.light.simplewebserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A very simple web server
 *
 * @author luxiaocong
 * @createdOn 2020/11/6
 */
public class SimpleWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            byte[] bytes = new byte[2048];
            int i = input.read(bytes);
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < i; j++) {
                buffer.append((char) bytes[j]);
            }
            System.out.println(buffer.toString());

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<h1>Hello!</h1>");
            out.flush();
            socket.close();
        }
    }

}
