package com.knoldus.entity

import spray.json.DefaultJsonProtocol

case class Students(rollno: Int, name: String, address: String, subjects: Int, course: String)

trait StudentJson extends DefaultJsonProtocol {

  //using jsonFormat5 because Students case class has 5 parameters
  implicit val studentFormat = jsonFormat5(Students)

}