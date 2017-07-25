package com.young.spider.common.crawler

import com.young.spider.common.bean.HttpSeed
import com.young.spider.core.crawler.AbstractCrawler
import com.young.spider.core.http.Request

/**
  * Created by yangyong3 on 2017/7/17.
  */
class CommonCrawler extends AbstractCrawler[HttpSeed]{
  override def getRequest(meta: HttpSeed): Request = {
    val request = new Request
    request.setUrl(meta.url)
    request
  }
}
