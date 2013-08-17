import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 *
 * Mockito use case
 *
 * User: jameshoare
 * Date: 17/08/2013
 * Project: scalapit
 *
 */
class ProductTestsMock extends FunSuite with BeforeAndAfter with MockitoSugar {


  test ("test Productservice") {

    val productService = mock[ProductService]
    when(productService.getProduct(1)).thenReturn(Some(Product(1,1,"shoe")))


  }




}


trait ProductService {

  def getProduct(productID : Int): Option[Product]

}

