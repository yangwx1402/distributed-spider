package com.young.spider.core.crawler

import com.young.spider.core.message.{CrawlerConfig, CrawlerEntity}
import com.young.spider.core.http.{Response, Request}
import com.young.spider.core.utils.ClassUtils

/**
  * Created by yangyong3 on 2017/7/7.
  */
trait Crawler[Meta] {

  @throws[CrawlerException]
  def crawl(meta: Meta,config:CrawlerConfig): CrawlerEntity[Meta]
}

object CrawlerFactory {
  def getCrawler[Meta](crawlerClass: String): Crawler[Meta] = ClassUtils.getInstance(crawlerClass)

}
