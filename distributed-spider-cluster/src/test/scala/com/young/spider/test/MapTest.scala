package com.young.spider.test

import scala.collection.mutable

/**
  * Created by yangyong3 on 2017/7/12.
  */
object MapTest {

  def main(args: Array[String]) {
    val map = mutable.Map[Integer,String]()
    map+=((1,"s"))
    println(map)
  }
}
