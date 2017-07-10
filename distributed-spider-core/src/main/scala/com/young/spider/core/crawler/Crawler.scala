package com.young.spider.core.crawler

import com.young.spider.core.message.CrawlerEntity
import com.young.spider.http.{Response, Request}

/**
  * Created by yangyong3 on 2017/7/7.
  */
trait Crawler[Meta] {

  @throws[CrawlerException]
  def crawl(meta: Meta): CrawlerEntity[Meta]
}
