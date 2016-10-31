package com.jroliveira.hangman.spec.domain.components

import com.jroliveira.hangman.domain.entities.Game
import com.jroliveira.hangman.spec.{BaseSpec, TestEnvironment}
import com.mongodb.casbah.commons.MongoDBObject

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class GetGameComponentSpec extends BaseSpec {
  val testEnvironment = new TestEnvironment

  "GetGame" should {
    get

    "access games' collection" in {
      there was one(testEnvironment.mongoDb).collection("games")
    }

    "findOne in moves' collection" in {
      there was two(testEnvironment.mongoCollection).findOne(MongoDBObject("id" -> "001"))
    }
  }

  def get: Option[Game] = {
    Await.result(testEnvironment.getGame("001"), Duration.Inf)
  }
}
