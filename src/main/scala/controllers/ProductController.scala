package controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import model.ProductServiceEntities.Product
import services.ProductService
import model.JsonProtocol._

trait ProductController extends ProductService {
  val productsRoutePath = "products"

  val productsRoute = {
    path(productsRoutePath) {
      get {
        complete(getAllProducts())
      } ~ post {
        entity(as[Product]) { product =>
          complete(createProduct(product))
        }
      }
    } ~
      (get & path(productsRoutePath / LongNumber)) { id =>
        complete(getProductDetails(id))
      } ~ (patch & path(productsRoutePath / LongNumber)) { id =>
      complete(updateProduct(id))
    } ~ (delete & path(productsRoutePath / LongNumber)) { id =>
      complete(deleteProduct(id))
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
