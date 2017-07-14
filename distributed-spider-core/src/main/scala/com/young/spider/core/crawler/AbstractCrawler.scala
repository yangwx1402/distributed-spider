package com.young.spider.core.crawler

import com.young.spider.core.message.{CrawlerConfig, CrawlerEntity}
import com.young.spider.core.http._

/**
  * Created by yangyong3 on 2017/7/7.
  */
abstract class AbstractCrawler[Meta](walkerFactory: AbstractHttpWalkerFactory = new DefaultHttpWalkerFactory) extends Crawler[Meta] {
  @throws[CrawlerException]
  override def crawl(meta: Meta,config:CrawlerConfig): CrawlerEntity[Meta] = {
    val request = getRequest(meta)
    var response: Response = null
    if (HttpMethod.GET == request.getMethod) {
      response = walkerFactory.getInstance().get(request)
    } else if (HttpMethod.POST == request.getMethod) {
      response = walkerFactory.getInstance().post(request)
    }
    CrawlerEntity(meta, response.getContent)
  }

  def getRequest(meta: Meta): Request
}
