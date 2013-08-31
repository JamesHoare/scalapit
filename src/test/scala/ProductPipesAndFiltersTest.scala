import akka.actor._
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.matchers.MustMatchers
import org.scalatest.WordSpec
import scala.Product


/**
 *
 * Basic Pipes and Filters
 *
 * User: jameshoare
 * Date: 26/08/2013
 * Project: scalapit
 *
 */
class ProductPipesAndFiltersTest extends TestKit(ActorSystem("testsystem"))
with WordSpec
with MustMatchers
with StopAkkaSystem {


  case class Product(id: String, description: String)


  class ProductDescriptionFilter(description: String, pipe: ActorRef) extends Actor {
    def receive = {
      case msg: Product =>
        if (msg.description == "clothing")
          pipe ! msg
    }
  }

  class ProductIdFilter(pipe: ActorRef) extends Actor {
    def receive = {
      case msg: Product =>
        if (!msg.id.isEmpty)
          pipe ! msg
    }
  }


  val endProbe = TestProbe()

  val productDescriptionRef = system.actorOf(
    Props(new ProductDescriptionFilter("Product Description", endProbe.ref)))

  val productIdRef = system.actorOf(
    Props(new ProductIdFilter(productDescriptionRef)))
  val msg = new Product("123xyz", "clothing")

  productIdRef ! msg
  endProbe.expectMsg(msg)

  productIdRef ! new Product("2", "dress")
  endProbe.expectNoMsg()

  productIdRef ! new Product("123xyz", "shoes")
  endProbe.expectNoMsg()


}








