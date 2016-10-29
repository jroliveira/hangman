package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.IdentityGeneratorComponent
import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.{Challenge, Game}
import salat._
import salat.global._

import scala.concurrent.Future

trait CreateGameComponent extends Component {
  this: IdentityGeneratorComponent
    with MongoDbComponent =>

  val createGame: CreateGame

  class CreateGame {
    def apply(player: String, challenge: Challenge): Future[Game] = {
      Future {
        val id = identityGenerator.generate()
        val game = Game(id, player, challenge)

        mongoDb.collection("games") += grater[Game].asDBObject(game)
        game
      }
    }
  }

}

