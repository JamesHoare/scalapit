/**
 * Bootstrap Product
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:52
 *
 */
class ProductMain extends App {

  val product = Product(1, 1, "shoe")
    assert(product.sku == 1)
    assert(product.price == 1)
    assert(product.description.equals("shoe"))


}
