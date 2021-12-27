package com.knoldus.Server

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.Entity._
import com.knoldus.Service.CrudOperation._
import com.knoldus.db.DBConnection._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.concurrent.Future
import scala.io.StdIn

object AkkaHttpServer extends App {

  implicit val system = ActorSystem("CURD-Application")
  implicit val materializer = ActorMaterializer()


  val routeForCrudApplication: Route =
    get {
      path("getStudents") { //read
        complete(readFromCassandra("select JSON * from Students"))
      }
    } ~ post {
      path("insertStudent") { //create
        entity(as[Students]) { student =>
          val saved: Future[Done] = writeToCassandra(student)
          onComplete(saved) { done => complete("Student record inserted into the database ---") }
        }
      }
    } ~ delete {
      path("removeStudent" / IntNumber) { //delete
        id =>
          val deleted: Future[Done] = deleteFromCassandra(id)
          onComplete(deleted) { done => complete("1 Row deleted...... ") }

      }
    } ~
      path("updateStudent" / IntNumber / Segment) { //update
        (no, course) =>
          val updated: Future[Done] = updateStudentData(no, course)
          onComplete(updated) { done => complete(" Record updated") }
      }

  //Binding to the host and port
  val bindingFuture = Http().bindAndHandle(routeForCrudApplication, "localhost", 8080)
  //Http().newServerAt(interface = "localhost", port = 8443).enableHttps(httpsServerContext).bind(route)

  println(s"Server is running at http://localhost:8080 .....")
  StdIn.readLine() // Hit Enter to stop server

  bindingFuture
    .flatMap(_.unbind()) //Unbinding the port
    .onComplete(_ ⇒ system.terminate()) // System will terminate when done

  //best practice
  cluster.close()
}
