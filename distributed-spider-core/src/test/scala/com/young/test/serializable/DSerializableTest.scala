package com.young.test.serializable

import com.young.spider.core.serialization.support.{KyroDSerialization, JavaDSerialization}

/**
  * Created by yangyong3 on 2017/7/18.
  */
case class User(name:String,age:Int) extends Serializable
object DSerializableTest {

  val dSerializable = new KyroDSerialization[User](classOf[User])

  def testSeri(): Array[Byte] ={
      val user = User("杨勇",30)
      dSerializable.serialization(user)
  }

  def testUnSer(bytes:Array[Byte]):User={
      dSerializable.unSerialization(bytes)
  }

  def main(args: Array[String]) {
    val array = DSerializableTest.testSeri()
    println(array.length)
    val user = DSerializableTest.testUnSer(array)
    println(user.name)
  }

}
