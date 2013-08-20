import akka.actor.{Props, ActorRef, Actor, ActorSystem}
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.{WordSpec}
import org.scalatest.matchers.MustMatchers

/**
 *
 * Learning Akka Test Kit
 *
 * User: jameshoare
 * Date: 19/08/2013
 * Project: scalapit
 *
 */
class ProductActorTest extends TestKit(ActorSystem("testsystem"))
with WordSpec
with MustMatchers
with StopAkkaSystem {
  "A Product Actor" must {
    "change state when it receives a message, single threaded" in {
      import ProductActorProtocol._
      val productActorRef = TestActorRef[ProductActor]
      productActorRef ! ProductStatusMessage("upcoming")
      productActorRef ! GetProductStatus(testActor)
      expectMsg(Vector("upcoming"))
      //productActorRef.underlyingActor.state must (contain("whisper"))
    }
    "change state when it receives a message, multi-threaded" in {
      import ProductActorProtocol._
      val productActorRef = system.actorOf(Props[ProductActor], "s3")
      productActorRef ! ProductStatusMessage("pre-order")
      productActorRef ! ProductStatusMessage("normal")
      productActorRef ! GetProductStatus(testActor)
      expectMsg(Vector("pre-order", "normal"))
    }
  }
}


object ProductActorProtocol {

  case class ProductStatusMessage(data: String)

  case class GetProductStatus(receiver: ActorRef)

}

class ProductActor extends Actor {

  import ProductActorProtocol._

  var internalState = Vector[String]()

  def receive = {
    case ProductStatusMessage(data) =>
      internalState = internalState :+ data

    case GetProductStatus(receiver) => receiver ! internalState

  }


}