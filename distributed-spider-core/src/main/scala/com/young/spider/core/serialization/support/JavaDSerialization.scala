package com.young.spider.core.serialization.support

import java.io.{ObjectInputStream, ObjectOutputStream}

import com.sun.xml.internal.messaging.saaj.util.{ByteInputStream, ByteOutputStream}
import com.young.spider.core.serialization.AbstractDSerialization

/**
  * Created by yangyong3 on 2017/7/18.
  * Java默认的序列化和反序列化实现
  */
class JavaDSerialization[FROM <: Serializable](clazz: Class[FROM]) extends AbstractDSerialization[FROM](clazz) {
  @throws[Exception]
  override def trasfer(from: FROM): Array[Byte] = {
    val byteOutputStream = new ByteOutputStream()
    val objectOutput = new ObjectOutputStream(byteOutputStream)
    objectOutput.writeObject(from)
    objectOutput.flush()
    objectOutput.close()
    byteOutputStream.getBytes
  }

  @throws[Exception]
  override def unTrasfer(bytes: Array[Byte]): FROM = {
    val byteInputStream = new ByteInputStream(bytes, bytes.length)
    val objectInput = new ObjectInputStream(byteInputStream)
    objectInput.readObject().asInstanceOf[FROM]
  }
}
