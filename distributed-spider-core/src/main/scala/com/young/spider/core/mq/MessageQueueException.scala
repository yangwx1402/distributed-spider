package com.young.spider.core.mq

/**
  * Created by yangyong3 on 2017/7/14.
  */
class MessageQueueException(message:String,throwable: Throwable) extends Exception{

  def this(message:String) = this(message,null)

  def this(throwable: Throwable) = this("",throwable)
}
