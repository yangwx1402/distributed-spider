package com.young.spider.cluster.example.group

import akka.actor.{ActorRef, Actor, ActorSystem, Props}
import akka.cluster.ClusterEvent.{MemberUp, MemberEvent, CurrentClusterState, MemberJoined}
import akka.cluster.{Cluster, Member}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random
import akka.pattern.ask

/**
  * Created by yangyong3 on 2017/7/11.
  */
class ClientRoleGroupActor extends Actor {

  val log = Logging.getLogger(this)

  val map = mutable.Map[Int, Member]()

  val rand = new Random()

  var serverCount = 0

  var client_sender:ActorRef = null

  val cluster = Cluster.get(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberJoined], classOf[MemberEvent])
  }

  override def receive: Receive = {
    case message: TextRequest =>
      println("client receive a request " + message)
      client_sender = sender()
      if (map.isEmpty) {
        sender() ! TextResponse(message.text, ResponseStatus("error", "Service unavailable, try again later"))
      } else {
        process(map.get(rand.nextInt(serverCount)).get, message)
      }
    case state: MemberUp =>
      log.info("member is up {}", state)
      val member = state.member
      if (member.hasRole("server")) {
        map.+=((serverCount, member))
        serverCount += 1
      }
      println("server list is {}",map)
    case message: TextResponse =>
      client_sender!message
    case other: Any => log.info("other message is {}", other)
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
