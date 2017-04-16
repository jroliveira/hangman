package com.jroliveira.hangman.routes

import com.jroliveira.hangman.Infra.ComponentRegistry
import com.jroliveira.hangman.models.Move.{MoveRequest, MoveResponse, MoveResponseMapper}

object MovesRoute extends Route {
  implicit val moveRequestFormat = jsonFormat1(MoveRequest)
  implicit val moveResponseFormat = jsonFormat3(MoveResponse)

  val routes =
    path("games" / Segment / "moves") { gameId =>
      post {
        entity(as[MoveRequest]) { request =>
          val getGame = ComponentRegistry.getGame
          val getMoves = ComponentRegistry.getMoves
          val performMove = ComponentRegistry.performMove

          val done =
            for {
              game <- getGame(gameId)
              _ <- performMove(request.letter, game.get)
              moves <- getMoves(game.get)
              gameUpdated <- getGame(gameId)
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
