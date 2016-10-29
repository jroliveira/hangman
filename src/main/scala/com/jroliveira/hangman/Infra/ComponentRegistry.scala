package com.jroliveira.hangman.Infra

import com.jroliveira.hangman.Infra.components.IdentityGeneratorComponentImpl
import com.jroliveira.hangman.Infra.components.data.MongoDbComponentImpl
import com.jroliveira.hangman.domain.components._

object ComponentRegistry extends MongoDbComponentImpl
  with IdentityGeneratorComponentImpl
  with CreateGameComponent
  with PerformMoveComponent
  with GetGameComponent
  with GetMovesComponent
  with GetChallengeComponent {

  val mongoDb = new Casbah
  val identityGenerator = new UUIDIdentityGenerator
  val createGame = new CreateGame
  val performMove = new PerformMove
  val getGame = new GetGame
  val getMoves = new GetMoves
  val getChallenge = new GetChallenge
}
