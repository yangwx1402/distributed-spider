package com.young.spider.core.http;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class Response implements Serializable{
    private Integer statusCode;

    private String content;

    private String encode;

    private String message;

    private InputStream inputStream;

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public Response(Integer statusCode, InputStream inputStream, String message, String encode) {
        this.statusCode = statusCode;
        this.inputStream = inputStream;
        this.message = message;
        this.encode = encode;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        if(!StringUtils.isBlank(content)){
            return content;
        }
        try {
            content = IOUtils.toString(inputStream,encode==null?"utf-8":encode);
            this.inputStream = new ByteArrayInputStream(content.getBytes(encode==null?"utf-8":encode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "statusCode = ["+statusCode+"], message is ["+message+"]";
    }
}
