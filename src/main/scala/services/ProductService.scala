package services

import spray.json.JsValue
import model.ProductServiceEntities.Product
import scala.concurrent.Future

trait ProductService {
  def getAllProducts(): Future[JsValue]

  def getProductDetails(productId: Long): Future[JsValue]

  def createProduct(product: Product): Future[JsValue]
}
