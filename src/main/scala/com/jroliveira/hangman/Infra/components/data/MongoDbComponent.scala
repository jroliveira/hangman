package com.jroliveira.hangman.Infra.components.data

import com.jroliveira.hangman.Infra.Config
import com.mongodb.ServerAddress
import com.mongodb.casbah.commons.TypeImports._
import com.mongodb.casbah.{MongoClient, MongoCollection, MongoCredential}
import salat._
import salat.global._

trait MongoDbComponent {
  val mongoDb: MongoDb

  trait MongoDb {
    def collection(collectionName: String): MongoCollection

    def in(collectionName: String): MongoDbOperation
  }

  trait MongoDbOperation {
    def save[O <: AnyRef](obj: O)
                         (implicit m: Manifest[O]): Unit

    def update[O <: AnyRef, Q](condition: Q, obj: O)
                              (implicit m: Manifest[O], queryView: Q => DBObject): Unit
  }

}

trait MongoDbComponentImpl extends MongoDbComponent {

  class Casbah extends MongoDb with Config {
    private val credentials = MongoCredential.createCredential(mongoUsername, mongoDatabase, mongoPassword)
    private val server = new ServerAddress(mongoHost, mongoPort)
    private val db = MongoClient(server, List(credentials))(mongoDatabase)

    override def collection(collectionName: String): MongoCollection = db(collectionName)

    override def in(collectionName: String): MongoDbOperation = new CasbahOperation(db(collectionName))
  }

  class CasbahOperation(mongoCollection: MongoCollection) extends MongoDbOperation {
    override def save[O <: AnyRef](obj: O)
                                  (implicit m: Manifest[O]): Unit = mongoCollection += grater[O].asDBObject(obj)

    override def update[O <: AnyRef, Q](condition: Q, obj: O)
                                       (implicit m: Manifest[O], queryView: Q => DBObject): Unit =
      mongoCollection
        .update(
          condition,
          grater[O].asDBObject(obj)
        )
  }

}