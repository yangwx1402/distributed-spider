package com.young.spider.common.parser

import com.young.spider.common.bean.HttpPage
import com.young.spider.core.message.{ParserEntity, ParserConfig, CrawlerEntity}
import com.young.spider.core.parser.Parser

/**
  * Created by yangyong3 on 2017/7/17.
  */
class DefaultParser extends Parser[String,HttpPage]{
  override def parse(crawlerEntity: CrawlerEntity[String], config: ParserConfig): ParserEntity[String, HttpPage] = {
    val html = crawlerEntity.body
  }
}
