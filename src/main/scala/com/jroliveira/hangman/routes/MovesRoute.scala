package com.jroliveira.hangman.routes

import com.jroliveira.hangman.Infra.ComponentRegistry
import com.jroliveira.hangman.models.Move.{GameRequest, MoveRequest, MoveResponse, MoveResponseMapper}

object MovesRoute extends Route {
  implicit val gameRequestFormat = jsonFormat1(GameRequest)
  implicit val moveRequestFormat = jsonFormat2(MoveRequest)
  implicit val moveResponseFormat = jsonFormat3(MoveResponse)

  val routes =
    path("moves") {
      post {
        entity(as[MoveRequest]) { request =>
          val getGame = ComponentRegistry.getGame
          val getMoves = ComponentRegistry.getMoves
          val performMove = ComponentRegistry.performMove

          val done =
            for {
              game <- getGame(request.game.id)
              _ <- performMove(request.letter, game.get)
              moves <- getMoves(game.get)
              gameUpdated <- getGame(request.game.id)
              response <- MoveResponseMapper(gameUpdated.get, moves.get)
            } yield response

          onSuccess(done) { response =>
            rejectEmptyResponse {
              complete {
                response
              }
            }
          }
        }
      }
    }
}
