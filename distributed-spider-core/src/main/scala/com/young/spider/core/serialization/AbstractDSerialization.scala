package com.young.spider.core.serialization

/**
  * Created by yangyong3 on 2017/7/18.
  * 序列化抽象实现
  */
abstract class AbstractDSerialization[T <: Serializable](clazz: Class[T]) extends DSerializable[T, Array[Byte]] {

  /**
    * 将对象序列化成字节数组
    * @param from
    * @throws Exception
    * @return
    */
  @throws[Exception]
  def trasfer(from: T): Array[Byte]

  /**
    * 从字节数组中反序列化对象
    * @param bytes
    * @throws Exception
    * @return
    */
  @throws[Exception]
  def unTrasfer(bytes:Array[Byte]):T

  @throws[DSerializationException]
  override def serialization(from: T): Array[Byte] = {
    try {
      trasfer(from)
    } catch {
      case e: Exception => throw new DSerializationException("serialization error object is [" + from + "]", e)
    }
  }

  @throws[DSerializationException]
  override def unSerialization(to: Array[Byte]): T = {
    try {
       unTrasfer(to)
    } catch {
      case e: Exception => throw new DSerializationException("unSerialization error bytes is [" + to + "]", e)
    }
  }
}
