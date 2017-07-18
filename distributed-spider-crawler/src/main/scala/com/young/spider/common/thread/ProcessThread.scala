package com.young.spider.common.thread

import com.young.spider.core.mq.{MessageQueue, MessageQueueFactory}
import com.young.spider.core.process.SpiderProcesser

/**
  * Created by yangyong3 on 2017/7/18.
  */
class ProcessThread[Meta] extends SpiderProcesser[Meta]{
  override def getQueue[Meta](): MessageQueue[Meta] = MessageQueueFactory.createQueue("")
}
