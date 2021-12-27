package com.knoldus.db

import com.datastax.driver.core.Cluster

object DBConnection {

  // creating a cassandra connection and connect with the keyspace college
  val cluster = Cluster.builder.addContactPoint("localhost").build()
  val cassandraConnection = cluster.connect("College")

}
