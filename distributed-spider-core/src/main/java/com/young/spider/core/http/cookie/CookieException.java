package com.young.spider.core.http.cookie;

/**
 * Created by yangyong3 on 2017/7/14.
 */
public class CookieException extends Exception{
    public CookieException() {
    }

    public CookieException(String message) {
        super(message);
    }

    public CookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
