ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "CRUD-Applicaton-using-AkkaHttp-and-Cassandra"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.16",
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.1.1",
  "com.typesafe.akka" %% "akka-actor" % "2.6.16",
  "com.typesafe.akka" %% "akka-http" % "10.2.7",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.7",
  "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "1.1.0" ,
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.0-rc2",
  "com.lightbend.akka" %% "akka-stream-alpakka-elasticsearch" % "2.0.0",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.slf4j" % "slf4j-api" % "2.0.0-alpha5"
)

