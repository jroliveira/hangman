package com.jroliveira.hangman.models

import scala.concurrent.ExecutionContext.Implicits
import scala.concurrent.ExecutionContextExecutor

trait Model {
  implicit val ec: ExecutionContextExecutor = Implicits.global
}
