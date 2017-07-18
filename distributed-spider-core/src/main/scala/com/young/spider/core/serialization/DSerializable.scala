package com.young.spider.core.serialization

/**
  * Created by yangyong3 on 2017/7/18.
  */
trait DSerializable[FROM, TO] {
  @throws[DSerializationException]
  def serialization(from: FROM): TO

  @throws[DSerializationException]
  def unSerialization(to: TO): FROM
}
