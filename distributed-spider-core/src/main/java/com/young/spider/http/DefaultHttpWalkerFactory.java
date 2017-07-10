package com.young.spider.http;

import com.young.spider.http.support.HttpClientWalker;

/**
 * Created by yangyong3 on 2017/7/7.
 */
public class DefaultHttpWalkerFactory extends AbstractHttpWalkerFactory{
    @Override
    public HttpWalker getInstance() {
        return new HttpClientWalker();
    }
}
