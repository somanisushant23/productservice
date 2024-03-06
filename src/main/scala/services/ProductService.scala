package services

import spray.json.JsValue

import scala.concurrent.Future

trait ProductService {
  def getAllProducts(): Future[JsValue]

  def getProductDetails(productId: Long): Future[JsValue]
}
