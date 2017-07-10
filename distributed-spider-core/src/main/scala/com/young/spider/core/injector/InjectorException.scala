package com.young.spider.core.injector

/**
  * Created by yangyong3 on 2017/7/7.
  */
class InjectorException(message: String, throwable: Throwable) extends Exception {
  def this(message: String) = this(message, null)

  def this(throwable: Throwable) = this("", throwable)
}
