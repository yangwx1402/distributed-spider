package com.young.spider.core.message

/**
  * Created by yangyong3 on 2017/7/14.
  */
case class SpiderConfig(crawler: CrawlerConfig, parser: ParserConfig, persister: PersisterConfig)

case class CrawlerConfig(crawler: String, retry: Int = 3)

case class ParserConfig(parser: String)

case class PersisterConfig(persister: String)
