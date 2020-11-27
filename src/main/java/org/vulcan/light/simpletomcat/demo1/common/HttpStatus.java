package org.vulcan.light.simpletomcat.demo1.common;

/**
 * @author luxiaocong
 * @createdOn 2020/11/26
 */
public enum HttpStatus {

    /**
     * 200
     */
    STATUS_200(200, "OK"),
    /**
     * 201
     */
    STATUS_201(201, "OK"),
    /**
     * 404
     */
    STATUS_404(404, "File Not Found");

    private int sc;

    private String sm;

    HttpStatus(int sc, String sm) {
        this.sc = sc;
        this.sm = sm;
    }

    public static HttpStatus valueOf(int sc) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.sc == sc) {
                return status;
            }
        }
        return null;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

}
