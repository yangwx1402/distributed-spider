package com.young.spider.example

import com.young.spider.core.crawler.{AbstractCrawler, Crawler}
import com.young.spider.core.http.{HttpMethod, Request}
import com.young.spider.core.message.CrawlerConfig

/**
  * Created by yangyong3 on 2017/7/7.
  */
class CrawlerExample extends AbstractCrawler[String]{
  override def getRequest(meta: String): Request = {
    val request = new Request
    request.setMethod(HttpMethod.GET)
    request.setUrl("https://www.baidu.com")
  }
}
object CrawlerExample{
  def main(args: Array[String]) {
    val crawler :Crawler[String] = new CrawlerExample
    val entity = crawler.crawl("",CrawlerConfig(""))
    println(entity.body)
  }
}
