
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive0, Directives, ExceptionHandler, Rejection, RejectionHandler, Route}
import controllers.ProductController
import services.FakeStoreProductsService

import java.io.IOException


object Checking extends App  with FakeStoreProductsService with ProductServiceRoute {

  Http().bindAndHandle(productServiceRoutes, hostName, hostPort)

  override def apply(v1: Seq[Rejection]): Option[Route] = ???
}

trait ProductServiceRoute extends RejectionHandler with ProductServiceDirectives with ProductController {
  def productServiceRoutes: Route =
    combinedDirective {
      productsRoute ~ usersRoute
    }
}

trait GlobalExceptionHandlerTrait extends Directives {
    implicit val globalExHandler: ExceptionHandler = ExceptionHandler {
      case ex: IOException =>
        complete(StatusCodes.BadRequest, "Incorrect request from client")
      case ex: Throwable =>
        complete(StatusCodes.InternalServerError, "GlobalExceptionHandler: Something went wrong!!")

    }
}

trait ProductServiceDirectives extends GlobalExceptionHandlerTrait {
  def combinedDirective: Directive0 = {
    //val rejections = handleRejections(RejectionHandler(""))
    val exception = handleExceptions(globalExHandler)
    exception //& rejections
  }
}