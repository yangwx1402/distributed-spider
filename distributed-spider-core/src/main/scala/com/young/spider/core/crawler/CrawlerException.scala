package com.young.spider.core.crawler

/**
  * Created by yangyong3 on 2017/7/7.
  */
class CrawlerException(message: String, throwable: Throwable) extends Exception {
  def this(message: String) = this(message, null)

  def this(throwable: Throwable) = this("", throwable)
}
