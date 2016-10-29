package com.jroliveira.hangman.routes

import akka.http.scaladsl.server.Directives

object Router extends Directives {
  val routes =
    HomeRoute.routes ~
      GamesRoute.routes ~
      MovesRoute.routes
}
