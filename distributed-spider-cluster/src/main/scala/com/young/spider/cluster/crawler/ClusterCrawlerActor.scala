package com.young.spider.cluster.crawler

import akka.actor.Actor
import akka.cluster.ClusterEvent.{MemberRemoved, MemberEvent, MemberUp}
import akka.cluster.{Cluster, Member}
import com.young.spider.cluster.constants.ClusterRole
import com.young.spider.core.crawler.CrawlerFactory
import com.young.spider.core.http.Request

/**
  * Created by yangyong3 on 2017/7/18.
  */
class ClusterCrawlerActor extends Actor {

  private val cluster = Cluster.get(context.system)

  private val MEMBER_ADD = "add"

  private val MEMBER_REMOVED = "removed"

  private val parsersList = new java.util.ArrayList[Member]()

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberEvent])
  }

  /**
    * 处理Parser节点的增加和Removed事件
    *
    * @param member
    * @param op
    */
  private def memberChanged(member: Member, op: String): Unit = {
    if (member.hasRole(ClusterRole.PARSER)) {
      if (MEMBER_ADD.equals(op)) {
        parsersList.add(member)
      } else if (MEMBER_REMOVED.equals(op)) {
        parsersList.remove(member)
      }
    }
  }

  override def receive: Receive = {
    case state: MemberUp => memberChanged(state.member, MEMBER_ADD)
    case state: MemberRemoved => memberChanged(state.member, MEMBER_REMOVED)
    case request:Request=>
      val response = CrawlerFactory.getCrawler("").crawl()
  }
}
