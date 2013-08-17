import org.scalatest.{BeforeAndAfter, FunSpec}

/**
 *
 *
 * BDD Tests for Product
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:43
 *
 */
class ProductTests extends FunSpec with BeforeAndAfter {

  var product: Product = _

  before {

    product = new Product(1, 12, "shoe")

  }


  describe("A Product should be created") {
    it("Will have a stock of 1 and a price of 12 with a description of shoe") {
      assert(product.sku == 1)
      assert(product.price == 12)
      assert(product.description.equals("shoe"))
    }

  }


}
