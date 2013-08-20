/**
 *
 * User: jameshoare
 * Date: 19/08/2013
 * Project: scalapit
 *
 */
import org.scalatest.{ Suite, BeforeAndAfterAll }
import akka.testkit.TestKit

trait StopAkkaSystem extends BeforeAndAfterAll {
  this: TestKit with Suite =>
  override protected def afterAll() {
    super.afterAll()
    system.shutdown()
  }
}
