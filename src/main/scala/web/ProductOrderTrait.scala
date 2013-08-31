package web

import akka.actor.ActorRef
import spray.routing.HttpService
import akka.util.Timeout

/**
 *
 * User: jameshoare
 * Date: 26/08/2013
 * Project: scalapit
 *
 */
trait ProductOrderTrait extends HttpService {
  val productOrderRef: ActorRef
  implicit val timeout: Timeout = 1 second
  val myRoute = path("productOrderTest") {
    get {
      parameters('id.as[Long]).as(ProductId) {
        orderId =>
        //get status
          complete {
            val askFuture = productOrderRef ? ProductId
            askFuture.map {
              case result: TrackingOrder => {
                <statusResponse>
                  <id>
                    {result.id}
                  </id>
                  <status>
                    {result.status}
                  </status>
                </statusResponse>
              }
              case result: NoSuchOrder => {
                <statusResponse>
                  <id>
                    {result.id}
                  </id>
                  <status>ID is unknown</status>
                </statusResponse>
              }
            }
          }
      }
    } ~
      post {
        //add order
        entity(as[String]) {
          body =>
            val order = XMLConverter.createOrder(body.toString)
            complete {
              val askFuture = productOrderRef ? order
              askFuture.map {
                case result: TrackingOrder => {
                  <confirm>
                    <id>
                      {result.id}
                    </id>
                    <status>
                      {result.status}
                    </status>
                  </confirm>.toString()
                }
                case result: Any => {
                  <confirm>
                    <status>
                      Response is unknown
                      {result.toString()}
                    </status>
                  </confirm>.toString()

                }
              }
            }
        }
      }
  }
}

