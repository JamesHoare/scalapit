import akka.actor.{Props, ActorRef, Actor, ActorSystem}
import akka.testkit.{TestProbe, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import grizzled.slf4j.Logger
import org.scalatest.{WordSpec}
import org.scalatest.matchers.MustMatchers
import spray.json._
import DefaultJsonProtocol._

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


  val logger = Logger[this.type]

  "A Product Actor" must {
    "change state when it receives a message, single threaded" in {
      import ProductActorProtocol._
      val productActorRef = TestActorRef[ProductActor]
      productActorRef ! ProductStatusMessage("upcoming")
      productActorRef ! GetProductStatus(testActor)
      expectMsg(Vector("upcoming"))
      productActorRef.underlyingActor.internalState must (contain("upcoming"))
      //spray-json
      /* val jsonAst = List(1, 2, 3).toJson.prettyPrint
       logger.info(jsonAst)*/


    }
    "change state when it receives a message, multi-threaded" in {
      import ProductActorProtocol._
      val productActorRef = system.actorOf(Props[ProductActor], "s3")
      productActorRef ! ProductStatusMessage("pre-order")
      productActorRef ! ProductStatusMessage("normal")
      productActorRef ! GetProductStatus(testActor)
      expectMsg(Vector("pre-order", "normal"))

    }


    "test application.conf properties" in {
      // testing accessing properties from application.conf
      val config = ConfigFactory.load()
      val applicationVersion = config.getInt("ProductApplication.version")
      assert(applicationVersion == 20)
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