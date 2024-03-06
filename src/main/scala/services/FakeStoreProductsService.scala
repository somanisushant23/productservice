package services
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.stream.ActorMaterializer
import spray.json._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

trait FakeStoreProductsService extends ProductService {
  implicit val system       = ActorSystem("ProductControllerActor")
  implicit val materializer = ActorMaterializer()(system)

  import system.dispatcher
  override def getAllProducts(): Future[JsValue] = {
    val req        = Get("https://fakestoreapi.com/products")
    val respFuture = Http().singleRequest(req)
    val finalResp = respFuture
      .flatMap(_.entity.toStrict(2.seconds))
      .map(_.data.utf8String.parseJson)
    finalResp
  }
}
