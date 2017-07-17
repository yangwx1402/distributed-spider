package com.young.spider.core.config

import com.thoughtworks.xstream.annotations.{XStreamImplicit, XStreamAsAttribute, XStreamAlias}

/**
  * Created by yangyong3 on 2017/7/17.
  */
@XStreamAlias("spider")
case class SpiderConfig()
@XStreamAlias
case class SpiderMessageQueue(@XStreamAsAttribute classname:String,@XStreamAlias properties:SpiderProperties)

case class SpiderCrawler(@XStreamAsAttribute classname:String,@XStreamAlias properties:SpiderProperties)
@XStreamAlias
case class SpiderProperties(@XStreamImplicit(itemFieldName = "property") properties:Array[SpiderProperty])

@XStreamAlias("property")
case class SpiderProperty(@XStreamAsAttribute name:String,@XStreamAsAttribute value:String,@XStreamAsAttribute desc:String)
