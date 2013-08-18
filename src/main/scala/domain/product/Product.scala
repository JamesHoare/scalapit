package product

/**
 *
 *
 * domain.product.domain.product pojo containing the normal stuff
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Time: 14:38
 *
 */
case class Product(uniqueID: Int = 1, defaultPrice: Int = 100, defaultProductDescription: String = "domain.product defaultProductDescription") {
  /**
   * pre conditions
   */
  assert(uniqueID != 0 && defaultPrice != 0 && defaultProductDescription.length > 0)

  def getStock() = Sku

  override def toString: String = s"Product: $uniqueID $defaultPrice $defaultProductDescription"


}


case class Sku(availableStock: Int) {

  def stock = List(12345, 678910)

  def stockStatus = availableStock > 0


}





