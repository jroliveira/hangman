package com.jroliveira.hangman.Infra.components.data

import com.jroliveira.hangman.Infra.Config
import com.mongodb.ServerAddress
import com.mongodb.casbah.{MongoClient, MongoCollection, MongoCredential}

trait MongoDbComponent {
  val mongoDb: MongoDb

  trait MongoDb {
    def collection(collectionName: String): MongoCollection
  }

}

trait MongoDbComponentImpl extends MongoDbComponent {

  class Casbah extends MongoDb with Config {
    private val credentials = MongoCredential.createCredential(mongoUsername, mongoDatabase, mongoPassword)
    private val server = new ServerAddress(mongoHost, mongoPort)
    private val db = MongoClient(server, List(credentials))(mongoDatabase)

    def collection(collectionName: String): MongoCollection = db(collectionName)
  }

}