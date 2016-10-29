package com.jroliveira.hangman.Infra

import com.typesafe.config.ConfigFactory

trait Config {
  private val config = ConfigFactory.load()

  private val httpConfig = config.getConfig("http")
  protected val httpInterface = httpConfig.getString("interface")
  protected val httpPort = httpConfig.getInt("port")

  private val mongoConfig = config.getConfig("mongo")
  protected val mongoHost = mongoConfig.getString("host")
  protected val mongoPort = mongoConfig.getInt("port")
  protected val mongoDatabase = mongoConfig.getString("database")
  protected val mongoUsername = mongoConfig.getString("username")
  protected val mongoPassword = mongoConfig.getString("password").toCharArray
}
