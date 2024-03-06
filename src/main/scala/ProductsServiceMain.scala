
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive0, Directives, ExceptionHandler, Rejection, RejectionHandler, Route}
import akka.stream.StreamTcpException
import controllers.ProductController
import services.FakeStoreProductsService
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import model.CustomExceptions.ProductNotFoundException

import java.io.IOException


object Checking extends App  with FakeStoreProductsService with ProductServiceRoute {
  val hostName = "localhost"
  val hostPort = 8080
  Http().bindAndHandle(productServiceRoutes, hostName, hostPort)

  override def apply(v1: Seq[Rejection]): Option[Route] = ???
}

trait ProductServiceRoute extends RejectionHandler with ProductServiceDirectives with ProductController {
  def productServiceRoutes: Route =
    combinedDirective {
      productsRoute ~ usersRoute
    }
}

trait GlobalExceptionHandlerTrait extends Directives with DefaultJsonProtocol {
    implicit val jsonFormat = jsonFormat1(ExceptionMessage)
    implicit val globalExHandler: ExceptionHandler = ExceptionHandler {
      case ex: IOException =>
        complete(StatusCodes.BadRequest, ExceptionMessage("Incorrect request from client"))
      case ex: StreamTcpException =>
        complete(StatusCodes.InternalServerError, ExceptionMessage("Cannot reach 3rd party services right now."))
      case ex: ProductNotFoundException =>
        complete(StatusCodes.NotFound, ExceptionMessage(ex.errorDetails))
      case ex: Throwable =>
        complete(StatusCodes.InternalServerError, ExceptionMessage("Something went wrong!!"))

    }

  case class ExceptionMessage(errorDetails: String)
}

trait ProductServiceDirectives extends GlobalExceptionHandlerTrait {
  def combinedDirective: Directive0 = {
    //val rejections = handleRejections(RejectionHandler(""))
    val exception = handleExceptions(globalExHandler)
    exception //& rejections
  }
}