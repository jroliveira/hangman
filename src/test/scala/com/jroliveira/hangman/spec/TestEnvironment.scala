package com.jroliveira.hangman.spec

import com.jroliveira.hangman.Infra.components.IdentityGeneratorComponent
import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.components.{CreateGameComponent, GetGameComponent, GetMovesComponent, PerformMoveComponent}
import com.jroliveira.hangman.domain.entities.{Challenge, Game}
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection, MongoCursor}
import org.specs2.mock.Mockito

class TestEnvironment extends MongoDbComponent
  with IdentityGeneratorComponent
  with CreateGameComponent
  with PerformMoveComponent
  with GetGameComponent
  with GetMovesComponent
  with Mockito {

  val mongoCursor = mock[MongoCursor]

  val mongoCollection = mock[MongoCollection]
  mongoCollection.findOne(MongoDBObject("id" -> "001")) returns None

  val mongoDbOperation = mock[MongoDbOperation]

  val mongoDb = mock[MongoDb]
  mongoDb.collection("games") returns mongoCollection
  mongoDb.collection("moves") returns mongoCollection
  mongoDb.in("games") returns mongoDbOperation
  mongoDb.in("moves") returns mongoDbOperation

  val identityGenerator = mock[IdentityGenerator]
  identityGenerator.generate returns "001"

  val createGame = new CreateGame
  val performMove = new PerformMove
  val getGame = new GetGame
  val getMoves = new GetMoves
}

object Fakes {
  val fakeGame = Game(
    "001",
    "junior",
    Challenge(
      "1",
      "junior",
      List("What is my name?")
    )
  )
}