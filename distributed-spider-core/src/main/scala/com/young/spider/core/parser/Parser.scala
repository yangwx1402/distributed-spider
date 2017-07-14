package com.young.spider.core.parser

import com.young.spider.core.message.{ParserConfig, ParserEntity, CrawlerEntity}
import com.young.spider.core.utils.ClassUtils

/**
  * Created by yangyong3 on 2017/7/7.
  */
trait Parser[Meta, Result] {
  def parse(crawlerEntity: CrawlerEntity[Meta],config:ParserConfig): ParserEntity[Meta, Result]
}

object ParserFactory {
  def getParser[Meta, Result](parserClass: String): Parser[Meta, Result] = ClassUtils.getInstance(parserClass)
}
