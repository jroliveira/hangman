package com.jroliveira.hangman.routes

object HomeRoute extends Route {
  val routes =
    path("") {
      get {
        complete {
          "I'm working..."
        }
      }
    }
}
