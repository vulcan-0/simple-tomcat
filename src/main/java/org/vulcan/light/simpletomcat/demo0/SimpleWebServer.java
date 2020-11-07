package org.vulcan.light.simpletomcat.demo0;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            List<String> lines = new ArrayList<String>();
            String line = "";
            for (char c = (char) reader.read(); !line.equals("\n"); c = (char) reader.read()) {
                if (c != '\r') {
                    buffer.append(c);
                } else {
                    line = buffer.toString();
                    lines.add(line);
                    buffer.delete(0, buffer.length());
                }
            }
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            for (String str : lines) {
                out.println("<p>" + str + "</p>");
            }
            out.flush();
            socket.close();
        }
    }

}
