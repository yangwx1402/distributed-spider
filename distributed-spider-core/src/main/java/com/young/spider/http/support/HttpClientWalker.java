package com.young.spider.http.support;



import com.young.spider.http.HttpWalker;
import com.young.spider.http.Request;
import com.young.spider.http.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/15.
 */
public class HttpClientWalker extends AbstractHttpWalker implements HttpWalker {

    private String userAgent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2";

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2";

    private int timeout = 10000;

    private static final int DEFAULT_TIME_OUT = 10000;

    private int poolSize = 100;

    private static final int DEFAULT_POOL_SIZE = 100;

    private static final String DEFAULT_CHARSET = "utf-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpClientWalker.class);

    private HttpClientBuilder httpClientBuilder;

    public HttpClientWalker(String userAgent, int timeout, int poolSize, HttpClientProxy proxy) {
        this.userAgent = userAgent;
        this.timeout = timeout;
        this.poolSize = poolSize;
        RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        httpClientBuilder = HttpClients.custom().setUserAgent(userAgent).setMaxConnTotal(poolSize)
                .setMaxConnPerRoute(poolSize).setDefaultRequestConfig(config);
        if (proxy != null) {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(proxy.getProxyip(), proxy.getProxyPort()),//可以访问的范围
                    new UsernamePasswordCredentials(proxy.getProxyUser(), proxy.getProxyPass()));//用户名和密码

            httpClientBuilder = httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
            HttpHost proxyInstance = new HttpHost(proxy.getProxyip(), proxy.getProxyPort());// 设置代理ip
            httpClientBuilder = httpClientBuilder.setProxy(proxyInstance);
        }
    }

    public HttpClientWalker(String userAgent, int timeout) {
        this(userAgent, timeout, DEFAULT_POOL_SIZE);
    }

    public HttpClientWalker(String userAgent, int timeout, int poolSize) {
        this(userAgent,timeout,poolSize,null);
    }

    public HttpClientWalker() {
        this(DEFAULT_USER_AGENT,DEFAULT_TIME_OUT,DEFAULT_POOL_SIZE);
    }

    public HttpClientWalker(HttpClientProxy proxy){
        this(DEFAULT_USER_AGENT,DEFAULT_TIME_OUT,DEFAULT_POOL_SIZE,proxy);
    }

    private HttpClient newHttpClient() {
        return httpClientBuilder.build();
    }


    public Response get(Request request) throws IOException {
        checkRequest(request);
        String url = processUrl(request.getUrl(), request.getParameters());
        HttpGet get = new HttpGet(url);
        return sendRequest(get, request);
    }

    public Response post(Request request) throws IOException {
        checkRequest(request);
        HttpPost post = new HttpPost(request.getUrl());
        HttpEntity entity = createEntity(request.getParameters(), request.getEncode() == null ? DEFAULT_CHARSET : request.getEncode());
        post.setEntity(entity);
        return sendRequest(post, request);
    }

    public Response delete(Request request) throws IOException {
        checkRequest(request);
        String url = processUrl(request.getUrl(), request.getParameters());
        HttpDelete delete = new HttpDelete(url);
        return sendRequest(delete, request);
    }

    private Response sendRequest(HttpUriRequest uriRequest, Request request) throws IOException {
        if (request.getHeader() != null && !request.getHeader().isEmpty()) {
            for (Map.Entry<String, String> entry : request.getHeader().entrySet()) {
                uriRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HttpResponse response = newHttpClient().execute(uriRequest);
        return new Response(response.getStatusLine().getStatusCode(), response.getEntity().getContent(), response.getStatusLine().getReasonPhrase(), request.getEncode());
    }

    private HttpEntity createEntity(Map<String, String> params, String encode)
            throws UnsupportedEncodingException {
        if (params == null) {
            return null;
        }
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            args.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(args, encode);
        return entity;
    }

    private String processUrl(String url, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue();
            }
        }
        return url;
    }

    public static class HttpClientProxy {

        private String proxyip;
        private int proxyPort;
        private String proxyUser;
        private String proxyPass;

        public String getProxyip() {
            return proxyip;
        }

        public int getProxyPort() {
            return proxyPort;
        }

        public String getProxyUser() {
            return proxyUser;
        }

        public String getProxyPass() {
            return proxyPass;
        }

        public HttpClientProxy(String proxyip, int proxyPort, String proxyUser, String proxyPass) {
            this.proxyip = proxyip;
            this.proxyPort = proxyPort;
            this.proxyUser = proxyUser;
            this.proxyPass = proxyPass;
        }
    }
}
