package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.Game
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import salat._
import salat.global._

import scala.concurrent.Future

trait GetGameComponent extends Component {
  this: MongoDbComponent =>

  val getGame: GetGame

  class GetGame {
    def apply(id: String): Future[Option[Game]] = {

      Future {
        val condition = MongoDBObject("id" -> id)

        mongoDb
          .collection("games")
          .findOne(condition)
          .map(grater[Game].asObject(_))
      }
    }
  }

}


