package Product

/**
 *
 *
 * Product.Product pojo containing the normal stuff
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:38
 *
 */
case class Product(sku : Int, price: Int, description : String) {

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

