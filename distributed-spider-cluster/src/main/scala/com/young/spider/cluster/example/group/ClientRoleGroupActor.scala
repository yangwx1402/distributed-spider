package com.young.spider.cluster.example.group

import akka.actor.{Props, ActorSystem, Actor, ActorRef}
import akka.cluster.ClusterEvent.{MemberJoined, MemberUp, CurrentClusterState}
import akka.cluster.{Cluster, Member, MemberStatus}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random
import akka.pattern.ask

/**
  * Created by yangyong3 on 2017/7/11.
  */
class ClientRoleGroupActor extends Actor {

  val map = mutable.Map[Int, Member]()

  val rand = new Random()

  var serverCount = 0

  val cluster = Cluster.get(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self,classOf[MemberJoined])
  }

  override def receive: Receive = {
    case message: TextRequest =>
      println("client receive a request " + message)
      println(map.size)
      if (map.isEmpty) {
        sender() ! TextResponse(message.text, ResponseStatus("error", "Service unavailable, try again later"))
      } else {
        process(map.get(rand.nextInt(serverCount)).get, message)
      }
    case state: CurrentClusterState =>
      println("这里为什么不执行"+state.getMembers)
      val it = state.getMembers.iterator()
      while (it.hasNext) {
        val member = it.next()
        map.+((serverCount, member))
        serverCount += 1
      }
    case message: TextResponse =>
      println(message)
    case other: Any => unhandled(other)
  }

  private def process(member: Member, textRequest: TextRequest): Unit = {
    if (member.hasRole("server"))
      context.actorSelection(member.address + "/user/server") ! textRequest
  }
}


object ClientRoleGroupActor {
  def main(args: Array[String]) {
    val system = ActorSystem("cluster", ConfigFactory.load("group-client-application.conf"))
    val actor = system.actorOf(Props[ClientRoleGroupActor])
    Thread.sleep(10000)
    for (i <- 1 until 10) {
      val result = ask(actor, TextRequest("s" * i))(Duration(2, "s"))
      val ss = Await.result(result, Duration(2, "s"))
      println(ss)
    }
  }
}
