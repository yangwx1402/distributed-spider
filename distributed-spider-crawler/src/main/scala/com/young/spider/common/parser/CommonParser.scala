package com.young.spider.common.parser

import com.young.spider.common.bean.{HttpSeed, HttpPage}
import com.young.spider.core.message.{ParserEntity, ParserConfig, CrawlerEntity}
import com.young.spider.core.parser.Parser

/**
  * Created by yangyong3 on 2017/7/17.
  */
class CommonParser extends Parser[HttpSeed,HttpPage]{
  override def parse(crawlerEntity: CrawlerEntity[HttpSeed], config: ParserConfig): ParserEntity[HttpSeed, HttpPage] = {
    val html = crawlerEntity.body
    null
  }
}
