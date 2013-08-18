package actors

import akka.actor.{Props, ActorSystem, Actor}
import product.Product

/**
 *
 * Love actors :-)
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Project: scalapit
 *
 */
class ProductActor extends Actor {


  def receive: Actor.Receive = {

    case Product => println(Product)
    case createChild => createMyChildSkuActor
    case "created" => print("Hello")
    case _ => println("WTF")
  }




  def createMyChildSkuActor {

    println("creating child Actor")
    val skuActorRef = context.actorOf(Props[SkuActor], "skuActor")
    Thread.sleep(1000)
    skuActorRef ! "Just Born"
  }

}


class SkuActor extends Actor {

  def receive: Actor.Receive = {
    case "Just Born" => sender ! "created"
  }
}

/**
 * Create Actor System and Send some messages
 */
object ActorBootstrap extends App {

  case class createChild()


  val system = ActorSystem("ProductActorSystem")

  val ProductActorRef = system.actorOf(Props[ProductActor], name = "ProductActor")

  ProductActorRef ! Product
  ProductActorRef ! createChild
  ProductActorRef ! "Perl"


  system.shutdown()


}


