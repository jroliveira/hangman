package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.{Game, Move}
import com.mongodb.casbah.commons.MongoDBObject

import scala.concurrent.Future

trait PerformMoveComponent extends Component {
  this: MongoDbComponent =>

  val performMove: PerformMove

  class PerformMove {
    def apply(letter: Char, game: Game): Future[Either[String, Unit]] = {
      Future {
        game match {
          case _ if game.attempts >= game.challenge.attempts =>
            Left(s"Number of tries ${game.attempts} exceeded")

          case _ =>
            val move = Move(letter, game.id)
            mongoDb
              .in("moves")
              .save(move)

            if (wrongAnswer(letter, game)) {
              val newGame = game.copy(attempts = game.attempts + 1)
              val condition = MongoDBObject("id" -> game.id)
              mongoDb
                .in("games")
                .update(condition, newGame)
            }

            Right(())
        }
      }
    }

    private def wrongAnswer(letter: Char, game: Game): Boolean = game.challenge.answer.indexOf(letter) < 0
  }

}
