package com.young.spider.core.serialization.support

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.serializers.JavaSerializer
import com.young.spider.core.serialization.AbstractDSerialization

/**
  * Created by yangyong3 on 2017/7/18.
  * Kyro序列化和反序列化比Java原生的字节数小，性能也好，传输带宽更节省
  */
class KyroDSerialization[T <: Serializable](clazz: Class[T]) extends AbstractDSerialization[T](clazz) {

  private val kryo = new Kryo
  kryo.setReferences(false)
  kryo.register(clazz, new JavaSerializer())

  @throws[Exception]
  override def trasfer(from: T): Array[Byte] = {
    val bos = new ByteArrayOutputStream()
    val output = new Output(bos)
    kryo.writeClassAndObject(output, from)
    output.flush()
    output.clear()
    bos.toByteArray
  }

  @throws[Exception]
  override def unTrasfer(bytes: Array[Byte]): T = {
    val input = new Input(new ByteArrayInputStream(bytes))
    kryo.readClassAndObject(input).asInstanceOf[T]
  }
}
