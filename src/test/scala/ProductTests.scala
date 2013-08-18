import grizzled.slf4j.Logger
import org.scalatest.{FunSuite, BeforeAndAfter}
import product.{Sku, Product}


/**
 *
 *
 * BDD Tests for domain.product.domain.product
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:43
 *
 */
class ProductTests extends FunSuite with BeforeAndAfter {

  val logger = Logger[this.type]

  var product: Product = _
  var sku    : Sku = _

  before {

    product = Product(1, 12, "shoe")
    sku     = Sku(1)


  }


  test("Will have a stock of 1 and a defaultPrice of 12 with a defaultProductDescription of shoe") {
    logger.info(product)
    assert(product.uniqueID == 1)
    assert(product.defaultPrice == 12)
    assert(product.defaultProductDescription.equals("shoe"))
    assert(sku.stockStatus == true)
  }


  test("catching an assertion exception") {
    val thrown = intercept[AssertionError] {
      Product(0, 1, "")
    }
    assert(thrown.isInstanceOf[java.lang.AssertionError])
  }


}
