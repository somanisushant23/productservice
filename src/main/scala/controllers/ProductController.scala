package controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import services.ProductService

trait ProductController extends ProductService {

  val productsRoutePath = "products"
  val productsRoute = {
    path(productsRoutePath) {
      get {
        complete(getAllProducts())
      }
    } ~
      path(productsRoutePath / LongNumber) { id =>
        complete(getProductDetails(id.toLong))
      }
  }

  val usersRoute = {
    (get & path("users")) {
      reject
    } ~ {
      (post & path(productsRoutePath)) {
        //TODO return product with matching product id
        reject
      }
    }
  }
}
