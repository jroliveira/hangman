package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.domain.entities.Game
import com.jroliveira.hangman.{BaseSpec, TestEnvironment}

import scala.concurrent.Await
import scala.concurrent.duration._

class CreateGameSpec extends BaseSpec with TestEnvironment {
  "CreateGame" should {
    "return id equal to '001'" in {
      game.id must beEqualTo("001")
    }

    "return player equal to 'junior'" in {
      game.player must beEqualTo("junior")
    }

    "return hints equal to 'What is my name?'" in {
      game.challenge.hints must contain("What is my name?")
    }

    "return answer equal to 'junior'" in {
      game.challenge.answer must beEqualTo("junior")
    }

    "return challenge attempts equal to 6" in {
      game.challenge.attempts must beEqualTo(6)
    }

    "return attempts equal to 0" in {
      game.attempts must beEqualTo(0)
    }
  }

  def game: Game = {
    Await.result(createGame("junior", fakeGame.challenge), Duration.Inf)
  }
}
