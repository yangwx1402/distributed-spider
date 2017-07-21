package com.young.spider.test.cluster.group

/**
  * Created by yangyong3 on 2017/7/11.
  */
sealed trait Message

case class TextRequest(text:String) extends Message

case class ResponseStatus(status:String,reason:String)

case class TextResponse(result:String,responseStatus: ResponseStatus) extends Message

case object RegisterServer extends Message




