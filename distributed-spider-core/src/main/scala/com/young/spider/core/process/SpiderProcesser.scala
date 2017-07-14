package com.young.spider.core.process

import com.young.spider.core.crawler.{Crawler, CrawlerFactory}
import com.young.spider.core.message.SpiderConfig
import com.young.spider.core.mq.MessageQueue
import com.young.spider.core.parser.{Parser, ParserFactory}
import com.young.spider.core.persist.{Persister, PersisterFactory}

/**
  * Created by yangyong3 on 2017/7/14.
  */
trait SpiderProcesser[Meta] {

  def getQueue(): MessageQueue[Meta]

  def process[Result](config: SpiderConfig): Unit = {
    val crawler: Crawler[Meta] = CrawlerFactory.getCrawler(config.crawler.crawler)
    val parser: Parser[Meta, Result] = ParserFactory.getParser(config.parser.parser)
    val persist: Persister[Meta, Result] = PersisterFactory.getPersist(config.persister.persister)
    try {
      val meta = getQueue().poll()
      val crawlerEntity = crawler.crawl(meta, config.crawler)
      val parserEntity = parser.parse(crawlerEntity, config.parser)
      persist.persist(meta, parserEntity, config.persister)
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}
