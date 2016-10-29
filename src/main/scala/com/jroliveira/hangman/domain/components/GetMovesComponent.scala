package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.{Game, Move}
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import salat._
import salat.global._

import scala.concurrent.Future

trait GetMovesComponent extends Component {
  this: MongoDbComponent =>

  val getMoves: GetMoves

  class GetMoves {
    def apply(game: Game): Future[Option[List[Move]]] = {

      Future {
        val condition = MongoDBObject("gameId" -> game.id)

        Some {
          mongoDb
            .collection("moves")
            .find(condition)
            .map(grater[Move].asObject(_))
            .toList
        }
      }
    }
  }

}
