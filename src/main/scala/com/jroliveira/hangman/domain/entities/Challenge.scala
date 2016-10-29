package com.jroliveira.hangman.domain.entities

case class Challenge(id: String,
                     answer: String,
                     hints: List[String],
                     attempts: Int = 6)
