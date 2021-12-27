package com.knoldus.Entity

import spray.json.DefaultJsonProtocol

trait StudentJson extends DefaultJsonProtocol {

  //using jsonFormat5 because Students case class has 5 parameters
  implicit val studentFormat = jsonFormat5(Students)

}
