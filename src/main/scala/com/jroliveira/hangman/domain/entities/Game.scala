package com.jroliveira.hangman.domain.entities

case class Game(id: String,
                player: String,
                challenge: Challenge,
                attempts: Int = 0)
