package integration

import akka.camel.{CamelMessage, Consumer}
import akka.actor.{ActorSystem, Props, Actor}
import grizzled.slf4j.Logger

/**
 *
 * Protocol adapters for system integration, simple example of using camel file adapter with Akka
 *
 * User: jameshoare
 * Date: 18/08/2013
 * Project: scalapit
 *
 */
class ProductConsumer extends Consumer {

  val logger = Logger[this.type]


  def endpointUri = "file:///Users/jameshoare/test"

  def receive = {
    case msg: CamelMessage ⇒ println("received %s" format msg.bodyAs[String])
  }


}

object ActorBootstrap extends App {

  val system = ActorSystem("file-akka-system")
  val fileActorRef = system.actorOf(Props[ProductConsumer])


}



