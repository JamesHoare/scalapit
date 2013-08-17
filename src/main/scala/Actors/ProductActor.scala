package Actors

import akka.actor.{Props, ActorSystem, Actor}

/**
  *
  * Love Actors :-)
  *
  * User: jameshoare
  * Date: 17/08/2013
  * Project: scalapit
  *
  */
class ProductActor extends Actor {

   def receive: Actor.Receive = {

     case Product => println("Product.Product received")
     case _       => println("WTF")
   }

 }

object main extends App {

  val system = ActorSystem("ProductActorSystem")

  val ProductActorRef = system.actorOf(Props[ProductActor], name = "ProductActor")

  ProductActorRef !  Product
  ProductActorRef !  "Perl"

  system.shutdown()


}