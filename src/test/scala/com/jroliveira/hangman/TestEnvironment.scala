package com.jroliveira.hangman

import com.jroliveira.hangman.Infra.components.IdentityGeneratorComponent
import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.components.{CreateGameComponent, PerformMoveComponent}
import com.jroliveira.hangman.domain.entities.{Challenge, Game}
import com.mongodb.casbah.MongoCollection
import org.specs2.mock.Mockito

trait TestEnvironment extends MongoDbComponent
  with IdentityGeneratorComponent
  with CreateGameComponent
  with PerformMoveComponent
  with Mockito {

  val fakeGame = Game(
    "001",
    "junior",
    Challenge(
      "1",
      "junior",
      List("What is my name?")
    )
  )

  val mongoCollection = mock[MongoCollection]
  val mongoDb = mock[MongoDb]
  mongoDb.collection("games") returns mongoCollection
  mongoDb.collection("moves") returns mongoCollection

  val identityGenerator = mock[IdentityGenerator]
  identityGenerator.generate returns "001"

  val createGame = new CreateGame
  val performMove = new PerformMove
}