package com.young.spider.core.mq.support.simple

import java.util.concurrent.{BlockingQueue, PriorityBlockingQueue}

/**
  * Created by yangyong3 on 2017/7/18.
  */
class SimpleProrityMessageQueue[T](name: String, capacity: Int = 1000) extends AbstractSimpleQueue[T](name, capacity) {

  private val queue = new PriorityBlockingQueue[T](capacity)

  override def getQueue(): BlockingQueue[T] = queue
}
