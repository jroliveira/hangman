package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.{Game, Move}
import com.mongodb.casbah.commons.MongoDBObject
import salat._
import salat.global._

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
            mongoDb.collection("moves") += grater[Move].asDBObject(move)

            if (wrongAnswer(letter, game)) {
              val newGame = game.copy(attempts = game.attempts + 1)
              val condition = MongoDBObject("id" -> game.id)
              mongoDb
                .collection("games")
                .update(
                  condition,
                  grater[Game].asDBObject(newGame)
                )
            }

            Right(())
        }
      }
    }

    private def wrongAnswer(letter: Char, game: Game): Boolean = game.challenge.answer.indexOf(letter) < 0
  }

}
