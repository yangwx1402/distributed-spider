package com.young.spider.core.mq.support.simple

import java.util.concurrent.BlockingQueue

import com.young.spider.core.mq.{AbstractMessageQueue, MessageQueueException}

/**
  * Created by yangyong3 on 2017/7/18.
  */
abstract class AbstractSimpleQueue[T](name: String, capacity: Int = 1000) extends AbstractMessageQueue[T](name, capacity) {

  def getQueue(): BlockingQueue[T]

  @throws[MessageQueueException]
  override def offer(message: T): Unit = {
    getQueue().offer(message)
  }

  @throws[MessageQueueException]
  override def size(): Int = getQueue().size()


  @throws[MessageQueueException]
  override def offer(messages: List[T]): Unit = messages.foreach(t => offer(t))


  @throws[MessageQueueException]
  override def poll(): T = getQueue().poll()


  @throws[MessageQueueException]
  override def poll(size: Int): List[T] = Array.fill(size)(poll()).filter(_ != null).toList

}
