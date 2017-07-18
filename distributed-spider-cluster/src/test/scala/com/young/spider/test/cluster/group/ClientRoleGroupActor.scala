package com.young.spider.test.cluster.group

import java.util

import akka.actor.{ActorRef, Actor, ActorSystem, Props}
import akka.cluster.ClusterEvent._
import akka.cluster.{Cluster, Member}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random
import akka.pattern.ask
import scala.collection.JavaConversions

/**
  * Created by yangyong3 on 2017/7/11.
  */
class ClientRoleGroupActor extends Actor {

  val log = Logging.getLogger(this)

  val servers = new util.ArrayList[Member]()

  val rand = new Random()

  var client_sender: ActorRef = null

  val cluster = Cluster.get(context.system)

  override def preStart(): Unit = {
    //注册client关心的消息类型
    cluster.subscribe(self, classOf[MemberJoined], classOf[MemberEvent], classOf[MemberRemoved])
  }

  override def receive: Receive = {
    //收到了业务方的请求
    case message: TextRequest =>
      println("client receive a request " + message)
      client_sender = sender()

      //如果没有可用的服务端
      if (servers.isEmpty) {
        sender() ! TextResponse(message.text, ResponseStatus("error", "Service unavailable, try again later"))
      } else {
        //有可用的服务端,就转发请求处理
        process(servers.get(rand.nextInt(servers.size())), message)
      }
    //节点移除后，将client缓存的服务端地址更新
    case state: MemberRemoved =>
      log.info("member is removed {}", state)
      val member = state.member
      if (member.hasRole("server")) {
        servers.remove(member)
      }
    //收到节点启动事件后看看是不是server服务端启动，如果是就将该server加入到client的缓存列表里
    case state: MemberUp =>
      log.info("member is up {}", state)
      val member = state.member
      if (member.hasRole("server")) {
        servers.add(member)
      }
      println("server list is {}", servers)
    //这里是收到了server返回的处理结果，那么将该处理结果返回给调用方。
    case message: TextResponse =>
      client_sender ! message
    case other: Any => log.info("other message is {}", other)
  }

  /**
    * 从client发送消息给server
    *
    * @param member
    * @param textRequest
    */
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
    for (i <- 1 until 50) {
      try {
        val result = ask(actor, TextRequest("s" * i))(Duration(2, "s"))
        val ss = Await.result(result, Duration(2, "s"))
        println(ss)
        Thread.sleep(2000)
      } catch {
        case e: Exception => println(e)
      }
    }
  }
}
