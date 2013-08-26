package integration

import akka.camel.{CamelMessage, Consumer}
import akka.actor.{ActorSystem, Props, Actor}
import grizzled.slf4j.Logger
import com.typesafe.config.ConfigFactory

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


  def endpointUri = "file:///" + System.getProperty("user.home") + "/test"

  def receive = {
    case msg: CamelMessage ⇒ println("received %s" format msg.bodyAs[String])
  }


}

object ActorBootstrap extends App {

  //loads application.conf can now access from configuration
  val configuration = ConfigFactory.load()
  val system = ActorSystem("file-akka-system",configuration)

  val fileActorRef = system.actorOf(Props[ProductConsumer])


}



