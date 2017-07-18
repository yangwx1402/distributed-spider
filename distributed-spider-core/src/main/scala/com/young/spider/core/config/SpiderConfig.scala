package com.young.spider.core.config

import com.thoughtworks.xstream.annotations.{XStreamImplicit, XStreamAsAttribute, XStreamAlias}

/**
  * Created by yangyong3 on 2017/7/17.
  */
@XStreamAlias("spider")
case class SpiderConfig()
@XStreamAlias("messageQueue")
case class SpiderMessageQueue(@XStreamAsAttribute classname:String,properties:SpiderProperties)

@XStreamAlias("crawler")
case class SpiderCrawler(@XStreamAsAttribute classname:String, properties:SpiderProperties)
@XStreamAlias("properties")
case class SpiderProperties(@XStreamImplicit(itemFieldName = "property") properties:Array[SpiderProperty])

@XStreamAlias("property")
case class SpiderProperty(@XStreamAsAttribute name:String,@XStreamAsAttribute value:String,@XStreamAsAttribute desc:String)
