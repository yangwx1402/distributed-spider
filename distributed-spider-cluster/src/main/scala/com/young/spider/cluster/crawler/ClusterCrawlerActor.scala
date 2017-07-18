package com.young.spider.cluster.crawler

import akka.actor.Actor
import akka.cluster.ClusterEvent.{MemberEvent, MemberUp}
import akka.cluster.{Cluster, Member}

/**
  * Created by yangyong3 on 2017/7/18.
  */
class ClusterCrawlerActor extends Actor{

  private val cluster = Cluster.get(context.system)

  private val parsersList = java.util.ArrayList[Member]

  override def preStart(): Unit ={
     cluster.subscribe(self,classOf[MemberEvent])
  }

  override def receive: Receive = {
    case state:MemberUp=>
    val member = state.member
      if(member.hasRole())
  }
}
