package com.young.spider.common.crawler

import com.young.spider.core.crawler.AbstractCrawler
import com.young.spider.core.http.Request

/**
  * Created by yangyong3 on 2017/7/17.
  */
class DefaultCrawler extends AbstractCrawler[String]{
  override def getRequest(meta: String): Request = {
    val request = new Request
    request.setUrl(meta)
  }
}
