package com.young.spider.core.serialization

/**
  * Created by yangyong3 on 2017/7/18.
  */
class DSerializationException(message:String,throwable: Throwable) extends Exception{

  def this(message:String) = this(message,null)

  def this(throwable: Throwable) = this("",throwable)
}
