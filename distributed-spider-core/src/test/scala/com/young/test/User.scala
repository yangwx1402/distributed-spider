package com.young.test

import com.young.spider.core.http.cookie.CookieFactory

import scala.util.Random

/**
  * Created by yangyong3 on 2017/7/18.
  */
case class User(name:String,age:Int)
object User{
  def main(args: Array[String]) {

    def test():String= {
      println("----")
      "luzhongyang"
    }

    val user = User("yangyong",1)
    println(user.name)
    println(test)
    println(CookieFactory.getCookier)

    val result = Array.fill(100)(new Random().nextInt(1000)).filter(_<100).map((_,1)).foreach(println _)
  }
}