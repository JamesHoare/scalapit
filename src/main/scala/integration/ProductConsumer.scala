package integration

import akka.camel.Consumer
import akka.actor.Actor

/**
 *
 * Protocol adapters for system integration
 *
 * User: jameshoare
 * Date: 18/08/2013
 * Project: scalapit
 *
 */
class ProductConsumer extends Consumer {


  def endpointUri: String = ???

  def receive: Actor.Receive = ???
}
