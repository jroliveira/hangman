package com.jroliveira.hangman.models.Game

import com.jroliveira.hangman.domain.entities.Game
import com.jroliveira.hangman.models.Model

import scala.concurrent.Future

final case class GameRequest(player: String)

final case class GameResponse(id: String,
                              hints: List[String],
                              preview: String)

object GameResponseMapper extends Model {
  def apply(game: Game): Future[GameResponse] = {
    Future {
      GameResponse(
        game.id,
        game.challenge.hints,
        "_" * game.challenge.answer.length
      )
    }
  }
}
