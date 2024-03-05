package services
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class FakeStoreProductsService extends ProductService {
  implicit val system       = ActorSystem("ProductControllerActor")
  implicit val materializer = ActorMaterializer()(system)

  import system.dispatcher
  override def getAllProducts(): Future[String] = {
    val req        = Get("https://fakestoreapi.com/products")
    val respFuture = Http().singleRequest(req)
    val finalResp = respFuture
      .flatMap(_.entity.toStrict(2.seconds))
      .map(_.data.utf8String)
    finalResp
  }
}
