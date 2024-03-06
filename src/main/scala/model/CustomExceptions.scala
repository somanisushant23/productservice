package model

object CustomExceptions {

  case class ProductNotFoundException(errorDetails: String) extends Exception

}
