package controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import services.ProductService

trait ProductController extends ProductService {

  val productsRoutePath = "products"
  val hostName          = "localhost"
  val hostPort          = 8080
  val productsRoute = {
    (get & path(productsRoutePath)) {
      complete(getAllProducts())
    } ~ {
      (post & path(productsRoutePath)) {
        //TODO return product with matching product id
        print("Rejection from Scala!!")
        throw new Exception("Bad exceptions")
        reject
      }
    }
  }

  val usersRoute = {
    (get & path("users")) {
      complete(getAllProducts())
    } ~ {
      (post & path(productsRoutePath)) {
        //TODO return product with matching product id
        reject
      }
    }
  }
}
