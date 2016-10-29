package com.jroliveira.hangman.routes

import com.jroliveira.hangman.Infra.ComponentRegistry
import com.jroliveira.hangman.models.Game.{GameRequest, GameResponse, GameResponseMapper}

object GamesRoute extends Route {
  implicit val requestFormat = jsonFormat1(GameRequest)
  implicit val responseFormat = jsonFormat3(GameResponse)

  val routes =
    path("games") {
      post {
        entity(as[GameRequest]) { request =>
          val getChallenge = ComponentRegistry.getChallenge
          val createGame = ComponentRegistry.createGame

          val done =
            for {
              challenge <- getChallenge()
              game <- createGame(request.player, challenge.get)
              response <- GameResponseMapper(game)
            } yield response

          onSuccess(done) { response =>
            complete {
              response
            }
          }
        }
      }
    }
}
