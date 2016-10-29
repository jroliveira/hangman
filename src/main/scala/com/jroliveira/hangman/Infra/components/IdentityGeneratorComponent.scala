package com.jroliveira.hangman.Infra.components

import java.util.UUID

trait IdentityGeneratorComponent {
  val identityGenerator: IdentityGenerator

  trait IdentityGenerator {
    def generate(): String
  }

}

trait IdentityGeneratorComponentImpl extends IdentityGeneratorComponent {

  class UUIDIdentityGenerator extends IdentityGenerator {
    override def generate(): String = UUID.randomUUID().toString
  }

}
