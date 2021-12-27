package com.knoldus.db

import com.datastax.driver.core.Cluster
import com.typesafe.config.ConfigFactory

object DBConnection {

  val conf = ConfigFactory.load()
  val HOST = conf.getString("cassandra.host")
  val KEYSPACE  = conf.getString("cassandra.keyspace")

  // creating a cassandra connection and connect with the keyspace college
  val cluster = Cluster.builder.addContactPoint(HOST).build()
  val cassandraConnection = cluster.connect(KEYSPACE)

}
