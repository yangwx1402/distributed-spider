import sbt.Keys._

logLevel := Level.Warn
val project_version = "1.0"
val akka_version = "2.4.8"
val httpClient_version = "4.4.1"
val commons_io_version = "2.4"
val commons_lang_version = "3.4"
val scala_version = "2.11.7"
val elastic4s_version = "2.3.0"
val jackson_version = "1.9.13"
val jsoup_version = "1.10.3"
val commons_logging_version = "1.2"
val jedis_version = "2.8.1"
val slf4j_version = "1.7.25"
val xstream_version = "1.4.9"
val codePro = Seq("-unchecked", "-deprecation", "-encoding", "utf8")
val resources = Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Maven Repository" at "http://repo1.maven.org/maven2/",
  "maven-restlet" at "http://maven.restlet.org",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")

//核心模块，爬虫框架
val spider_core = Project("distributed-spider-core", file("distributed-spider-core")).settings(
  name := "distributed-spider-core",
  version := project_version,
  resolvers ++= resources,
  scalaVersion := scala_version,
  scalacOptions := codePro,
  libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akka_version,
  libraryDependencies += "org.apache.httpcomponents" % "httpclient" % httpClient_version,
  libraryDependencies += "org.apache.httpcomponents" % "httpmime" % httpClient_version,
  libraryDependencies += "org.apache.httpcomponents" % "httpcore" % httpClient_version,
  libraryDependencies += "commons-io" % "commons-io" % commons_io_version,
  libraryDependencies += "org.apache.commons" % "commons-lang3" % commons_lang_version,
  libraryDependencies += "org.codehaus.jackson" % "jackson-core-asl" % jackson_version,
  libraryDependencies += "org.codehaus.jackson" % "jackson-mapper-asl" % jackson_version,
  libraryDependencies += "org.jsoup" % "jsoup" % jsoup_version,
  libraryDependencies += "commons-logging" % "commons-logging" % commons_logging_version,
  libraryDependencies += "redis.clients" % "jedis" % jedis_version,
  libraryDependencies += "org.slf4j" % "slf4j-api" % slf4j_version,
  libraryDependencies += "com.thoughtworks.xstream" % "xstream" % xstream_version
)
//爬虫
val spider_cralwer = Project("distributed-spider-crawler", file("distributed-spider-crawler")).settings(
  name := "distributed-spider-crawler",
  version := project_version,
  resolvers ++= resources,
  scalaVersion := scala_version,
  scalacOptions := codePro,
  libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4s_version
).dependsOn(spider_core)


val spider_cluster = Project("distributed-spider-cluster", file("distributed-spider-cluster")).settings(
  name := "distributed-spider-cluster",
  version := project_version,
  resolvers ++= resources,
  scalaVersion := scala_version,
  scalacOptions := codePro,
  libraryDependencies += "com.typesafe.akka" %% "akka-remote" % akka_version,
  libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % akka_version
).dependsOn(spider_core)

val root = Project("distributed-spider", file(".")).settings(publish := {}).settings(publishArtifact := false)
  .settings(
    organization in ThisBuild := "com.young.scala",
    name := "distributed-spider",
    resolvers ++= resources,
    version := project_version,
    scalaVersion := scala_version,
    scalacOptions := codePro
  ).aggregate(spider_cralwer)