package com.young.spider.core.mq

import com.young.spider.core.utils.ClassUtils

/**
  * Created by yangyong3 on 2017/7/7.
  * 消息队列
  */
trait MessageQueue[T] {

  @throws[MessageQueueException]
  def offer(message: T)

  @throws[MessageQueueException]
  def offer(messages: List[T])

  @throws[MessageQueueException]
  def poll(): T

  @throws[MessageQueueException]
  def poll(size: Int): List[T]

  @throws[MessageQueueException]
  def size(): Int

}

object MessageQueueFactory {
  def createQueue[T](queueClassName: String,args: AnyRef*): MessageQueue[T] = {
    ClassUtils.getInstance(queueClassName, args)
  }
}
