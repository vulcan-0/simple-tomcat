package org.vulcan.light.simpletomcat.demo1.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Class clazz;

    public Logger(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * debug
     *
     * @param message
     */
    public void debug(String message) {
        System.out.println(ANSI_BLUE + getLogMessage(message) + ANSI_RESET);
    }

    /**
     * info
     *
     * @param message
     */
    public void info(String message) {
        System.out.println(ANSI_GREEN + getLogMessage(message) + ANSI_RESET);
    }

    /**
     * warning
     *
     * @param message
     */
    public void warning(String message) {
        System.out.println(ANSI_YELLOW + getLogMessage(message) + ANSI_RESET);
    }

    /**
     * error
     *
     * @param message
     */
    public void error(String message) {
        System.out.println(ANSI_RED + getLogMessage(message) + ANSI_RESET);
    }

    /**
     * error
     *
     * @param message
     * @param throwable
     */
    public void error(String message, Throwable throwable) {
        System.out.println(ANSI_RED + getLogMessage(message));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        System.out.print(sw.toString());

        System.out.println(ANSI_RESET);
    }

    /**
     * console
     *
     * @param message
     * @param color
     */
    public void console(String message, String color) {
        System.out.println(color + getLogMessage(message) + ANSI_RESET);
    }

    private String getLogMessage(String message) {
        return this.clazz.getName() + "[Thread-" + Thread.currentThread().getId() + "] : " + message;
    }

}
