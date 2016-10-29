package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.domain.entities.Game
import com.jroliveira.hangman.{BaseSpec, TestEnvironment}

import scala.concurrent.Await
import scala.concurrent.duration._

class PerformMoveSpec extends BaseSpec with TestEnvironment {
  "PerformMove " should {
    "return right when you've got moves" in {
      performMove(fakeGame) must beRight(())
    }

    "return left('Number of tries 6 exceeded') when you've exceeded the moves" in {
      val game = fakeGame.copy(attempts = 6)
      performMove(game) must beLeft("Number of tries 6 exceeded")
    }
  }

  def performMove(game: Game): Either[String, Unit] = {
    Await.result(performMove('i', game), Duration.Inf)
  }
}
