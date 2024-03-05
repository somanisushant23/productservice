package services

import scala.concurrent.Future

trait ProductService {
  def getAllProducts(): Future[String]
}
