package com.young.spider.core.mq

import com.young.spider.core.utils.ClassUtils

/**
  * Created by yangyong3 on 2017/7/7.
  */
trait MessageQueue[T] {

  @throws[MessageQueueException]
  def offer(message: T)

  @throws[MessageQueueException]
  def poll(): T
}

object MessageQueueFactory {
  def getQueue[T](queueClassName: String, queueName: String): MessageQueue[T] = {
    ClassUtils.getInstance(queueClassName, queueName)
  }
}
