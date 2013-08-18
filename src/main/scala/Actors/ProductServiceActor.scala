package actors

import akka.actor.Actor
import product.Product


/**
 *
 * Middle tier between client and repository
 *
 * User: jameshoare
 * Date: 18/08/2013
 * Project: scalapit
 *
 */
class ProductServiceActor extends Actor {

  def receive: Actor.Receive = {
    case getProduct(id) =>
      val product = Product(id)
      sender ! product

  }


}


case class getProduct(productID: Int)

