package com.young.spider.common.bean

/**
  * Created by yangyong3 on 2017/7/17.
  */

case class HttpSeed(url:String,source:String,plguin:String="default")

case class HttpPage(title:String,body:String,sublinks:Array[String],header:Map[String,String])

