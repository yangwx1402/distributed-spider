package com.young.spider.http;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class Request implements Serializable {

    private HttpMethod method;

    private String url;

    private Map<String, String> header;

    private Map<String, String> parameters;

    private String encode;

    public Map<String, String> getHeader() {
        return header;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Request setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public Request setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Request setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public String getEncode() {
        return encode;
    }

    public Request setEncode(String encode) {
        this.encode = encode;
        return this;
    }

    public String toString(){
        return "url="+url+",header = "+header;
    }

}
