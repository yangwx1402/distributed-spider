package com.young.spider.core.message

/**
  * Created by yangyong3 on 2017/7/7.
  */
case class CrawlerEntity[Meta](meta:Meta,body:String)

case class ParserEntity[Meta,Result](crawlerEntity: CrawlerEntity[Meta])



