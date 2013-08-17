/**
 *
 *
 * Product pojo containing the normal stuff
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:38
 *
 */
case class Product(sku : Int, price: Int, description : String) {
  assert(sku != 0 && price != 0 && description.length > 0)

  override def equals(that: Any): Boolean = true
}

