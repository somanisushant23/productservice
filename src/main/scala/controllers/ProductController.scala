package controllers

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import services.{FakeStoreProductsService, ProductService}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.duration.DurationInt

object ProductController extends App {
  implicit val system       = ActorSystem("ProductControllerActor")
  implicit val materializer = ActorMaterializer()(system)
  import system.dispatcher

  val productsRoutePath = "products"
  val hostName          = "localhost"
  val hostPort          = 8080
  val productsRoute = {
    (get & path(productsRoutePath)) {
      val products = new FakeStoreProductsService()
      complete(products.getAllProducts())
    } ~ {
      (post & path(productsRoutePath)) {
        //TODO return product with matching product id
        reject
      }
    }
  }
  Http().bindAndHandle(productsRoute, hostName, hostPort)
}
