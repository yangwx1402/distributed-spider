package com.young.spider.cluster.example.group

import akka.actor.{Actor, ActorSystem, Props}
import akka.cluster.ClusterEvent.{MemberEvent, MemberJoined, CurrentClusterState, MemberUp}
import akka.cluster.{Cluster, Member, MemberStatus}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

/**
  * Created by yangyong3 on 2017/7/11.
  */
class ServerRoleGroupActor extends Actor {

  val log = Logging.getLogger(this)

  val cluster = Cluster.get(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberEvent])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case message: TextRequest => sender() ! TextResponse(message.text.toUpperCase, ResponseStatus("ok", ""))
    case state: CurrentClusterState =>
      val it = state.members
        println("sdfsfsdfsf"+state.members)
    case state:MemberUp=>log.info("haha member is up {}",state.member)
    case other: Any => log.info("other message is {}",other)
  }

}

object ServerRoleGroupActor {
  def main(args: Array[String]) {
    val system = ActorSystem("cluster",ConfigFactory.load("group-server-application.conf"))
    val actor = system.actorOf(Props[ServerRoleGroupActor], "server")
  }
}
