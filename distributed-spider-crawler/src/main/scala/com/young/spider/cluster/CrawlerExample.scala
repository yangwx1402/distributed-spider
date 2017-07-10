package com.young.spider.cluster

import com.young.spider.core.crawler.AbstractCrawler
import com.young.spider.http.Request

/**
  * Created by yangyong3 on 2017/7/7.
  */
class CrawlerExample extends AbstractCrawler[String]{
  override def getRequest(meta: String): Request = ???
}
