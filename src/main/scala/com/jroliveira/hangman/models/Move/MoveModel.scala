package com.jroliveira.hangman.models.Move

import com.jroliveira.hangman.domain.entities.{Game, Move}
import com.jroliveira.hangman.models.Model

import scala.concurrent.Future

final case class MoveRequest(letter: Char)

final case class MoveResponse(attempts: Int,
                              wrong: String,
                              preview: String)


object MoveResponseMapper extends Model {
  def apply(game: Game, moves: List[Move]): Future[MoveResponse] = {
    Future {
      val init = new Temp(game.challenge.answer)
      val temp =
        moves
          .foldLeft(init) { (acc, move) =>
            acc.answer.indexOf(move.letter) match {
              case pos if pos < 0 =>
                acc.wrong.append(s"${move.letter}, ")

              case pos =>
                acc.preview.replace(pos, pos + 1, move.letter.toString)
            }

            acc
          }

      MoveResponse(
        game.attempts,
        temp.wrong.toString,
        temp.preview.toString
      )
    }
  }

  class Temp(val answer: String) {
    val preview: StringBuilder = new StringBuilder("_" * answer.length)
    val wrong: StringBuilder = StringBuilder.newBuilder
  }

}
