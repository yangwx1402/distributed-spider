package com.young.spider.cluster.example.single

import akka.actor._
import akka.cluster.ClusterEvent.{MemberEvent, MemberRemoved, MemberUp, UnreachableMember}
import akka.cluster.{Cluster, ClusterEvent}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

/**
  * Created by yangyong3 on 2017/7/10.
  * 初步的认识下akka cluster
  */
class ClusterListenerActor extends Actor {

  val log = Logging.getLogger(context.system, this)

  val cluster = Cluster.get(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, ClusterEvent.initialStateAsEvents, classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive = {
    case message:MemberUp =>
      log.info("member is up {}", message.member)
    case message: UnreachableMember =>
      log.info("member is unreachable {}", message.member)
    case message: MemberRemoved =>
      log.info("member is removed {}", message.member)
    case message: MemberEvent =>
      log.info("message is {}", message)
    case any:Any => unhandled(any)
  }
}


object ClusterListenerActor {
  def main(args: Array[String]) {
    val system = ActorSystem("cluster",ConfigFactory.load("single-application.conf"))
    val actor = system.actorOf(Props[ClusterListenerActor])
  }
}
