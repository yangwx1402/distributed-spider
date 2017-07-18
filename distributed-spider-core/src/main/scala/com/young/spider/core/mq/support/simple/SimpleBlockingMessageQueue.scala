package com.young.spider.core.mq.support.simple

import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}

/**
  * Created by yangyong3 on 2017/7/18.
  */
class SimpleBlockingMessageQueue[T](name: String, capacity: Int = 1000) extends AbstractSimpleQueue[T](name, capacity) {
  private val queue = new LinkedBlockingQueue[T](capacity)

  override def getQueue(): BlockingQueue[T] = queue
}
