package services
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model.{HttpEntity, StatusCodes}
import akka.stream.ActorMaterializer
import model.CustomExceptions.ProductNotFoundException
import spray.json._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

trait FakeStoreProductsService extends ProductService {
  implicit val system       = ActorSystem("ProductControllerActor")
  implicit val materializer = ActorMaterializer()(system)
  import system.dispatcher

  val fakeStoreBaseUrl  = "https://fakestoreapi.com/"
  val fakeStoreProducts = "products"
  override def getAllProducts(): Future[JsValue] = {
    val req        = Get(s"$fakeStoreBaseUrl$fakeStoreProducts")
    val respFuture = Http().singleRequest(req)
    val finalResp = respFuture
      .flatMap(_.entity.toStrict(2.seconds))
      .map(_.data.utf8String.parseJson)
    finalResp
  }

  override def getProductDetails(productId: Long): Future[JsValue] = {
    val req        = Get(s"$fakeStoreBaseUrl$fakeStoreProducts/$productId")
    val respFuture = Http().singleRequest(req)
    respFuture.flatMap { response =>
      if (response.status == StatusCodes.OK) {
        response.entity.toStrict(2.seconds).flatMap { entity =>
          if (response.entity.asInstanceOf[HttpEntity.Strict].data.isEmpty) {
            Future.failed(ProductNotFoundException("Product not found error"))
          } else {
            Future.successful(entity.data.utf8String.parseJson)
          }
        }
      } else {
        Future.failed(new RuntimeException(response.entity.asInstanceOf[HttpEntity.Strict].data.utf8String))
      }
    }
  }
}
