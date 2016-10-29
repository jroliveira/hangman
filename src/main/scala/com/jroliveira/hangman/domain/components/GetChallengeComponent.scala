package com.jroliveira.hangman.domain.components

import com.jroliveira.hangman.Infra.components.data.MongoDbComponent
import com.jroliveira.hangman.domain.entities.Challenge

import scala.concurrent.Future

trait GetChallengeComponent extends Component {
  this: MongoDbComponent =>

  val getChallenge: GetChallenge

  class GetChallenge {
    def apply(): Future[Option[Challenge]] = {
      Future {
        Some {
          Challenge(
            "1",
            "junior",
            List("What is my name?")
          )
        }
      }
    }
  }

}
