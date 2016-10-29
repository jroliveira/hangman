package com.jroliveira.hangman.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext.Implicits
import scala.concurrent.ExecutionContextExecutor

trait Route extends Directives
  with SprayJsonSupport
  with DefaultJsonProtocol {

  implicit val ec: ExecutionContextExecutor = Implicits.global
}
