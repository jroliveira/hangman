package com.jroliveira.hangman.domain.components

import scala.concurrent.ExecutionContext.Implicits
import scala.concurrent.ExecutionContextExecutor

trait Component {
  implicit val ec: ExecutionContextExecutor = Implicits.global
}
