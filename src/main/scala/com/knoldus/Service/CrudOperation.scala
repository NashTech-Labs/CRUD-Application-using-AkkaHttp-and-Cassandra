package com.knoldus.Service

import akka.Done
import com.knoldus.Entity._
import com.knoldus.Server.AkkaHttpServer
import com.knoldus.db.DBConnection._
import spray.json._

import scala.concurrent.Future

object CrudOperation extends StudentJson {

  implicit val executionContext = AkkaHttpServer.system.dispatcher
  //read data
  def readFromCassandra(query: String): List[Students] = {
    var studentList: List[Students] = Nil
    val resultSet = cassandraConnection.execute(query)
    val it = resultSet.iterator()
    while (it.hasNext) {
      //Convert each row of json data to Student object
      val rowString = resultSet.one().getString(0)
      val studentObject = rowString.parseJson.convertTo[Students]

      //adding students to the list
      studentList = studentObject :: studentList
    }
    //returning the list
    studentList
  }

  //write data
  def writeToCassandra(students: Students) = {
    val query = "INSERT INTO Students (rollno,name,address,subjects,course)" +
      s"VALUES(${students.rollno},'${students.name}', '${students.address}', ${students.subjects}, '${students.course}') IF NOT EXISTS;"
    cassandraConnection.execute(query)
    Future {
      Done
    }
  }


  //delete an entire row from table having given id.
  def deleteFromCassandra(id: Int) = {
    val query = s"DELETE FROM Students where rollno = $id ;"
    cassandraConnection.execute(query)
    Future {
      Done
    }
  }


  /*
  Akka http didn't support bindAndHandle with UPDATE
  this method will update data in cassandra database.
  This update feature only valid with https
   */

  def updateStudentData(rollno: Int, course: String) = {
    val query = s"UPDATE Students SET address='Lucknow' WHERE rollno=$rollno and course=$course ;"
    cassandraConnection.execute(query)
    Future {
      Done
    }
  }


}
