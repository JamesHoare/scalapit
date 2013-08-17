package product

/**
 *
 *
 * product.product pojo containing the normal stuff
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:38
 *
 */
case class Product(sku : Int = 1, price: Int = 100, description : String = "product description") {

  /**
   * pre conditions
   */
  assert(sku != 0 && price != 0 && description.length > 0)

  override def equals(that: Any): Boolean = true

  override def toString: String = s"product $sku $price $description"

  /**
   * delegate toString
   */
  def printProduct{println(this)}
}

