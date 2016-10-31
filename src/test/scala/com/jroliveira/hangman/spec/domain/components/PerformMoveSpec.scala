package com.jroliveira.hangman.spec.domain.components

import com.jroliveira.hangman.domain.entities.Move
import com.jroliveira.hangman.spec.{BaseSpec, Fakes, TestEnvironment}
import com.mongodb.casbah.commons.MongoDBObject

import scala.concurrent.Await
import scala.concurrent.duration._

class PerformMoveSpec extends BaseSpec {
  val game = Fakes.fakeGame

  "PerformMove when you've got moves" should {
    val testEnvironment = new TestEnvironment
    val moved = Await.result(testEnvironment.performMove('i', game), Duration.Inf)

    "return right" in {
      moved must beRight(())
    }

    "access moves' collection" in {
      there was one(testEnvironment.mongoDb).in("moves")
    }

    "save move in moves' collection" in {
      val move = Move('i', game.id)

      there was one(testEnvironment.mongoDbOperation).save(move)
    }
  }

  "PerformMove when you missed the wrong answer" should {
    val testEnvironment = new TestEnvironment
    val moved = Await.result(testEnvironment.performMove('a', game), Duration.Inf)

    "return right" in {
      moved must beRight(())
    }

    "access games' collection" in {
      there was one(testEnvironment.mongoDb).in("games")
    }

    "update game in games' collection" in {
      val newGame = game.copy(attempts = game.attempts + 1)
      val condition = MongoDBObject("id" -> newGame.id)

      there was one(testEnvironment.mongoDbOperation).update(condition, newGame)
    }
  }

  "PerformMove when you've exceeded the moves" should {
    val testEnvironment = new TestEnvironment
    val newGame = game.copy(attempts = 6)
    val moved = Await.result(testEnvironment.performMove('a', newGame), Duration.Inf)

    "return left('Number of tries 6 exceeded')" in {
      moved must beLeft("Number of tries 6 exceeded")
    }
  }
}
