package com.young.spider.core.http.cookie;

import java.util.Map;

/**
 * Created by yangyong3 on 2017/7/14.
 */
public interface Cookier {

    Map<String, String> findCookie(CookieConfig config) throws CookieException;

    void storeCookie(CookieConfig config, Map<String, String> cookies) throws CookieException;

    void delCookie(CookieConfig config) throws CookieException;
}
