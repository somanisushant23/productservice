package model

import model.ProductServiceEntities.Product
import spray.json.DefaultJsonProtocol

object ProductServiceEntities {
  case class Product(title: String,
                     price: Double,
                     description: String,
                     image: String,
                     category: String) {
    require(title.nonEmpty && title.length < 50,
      "title cannot be empty and should be less than 50 characters")
    require(price > 0 && price <= 99999, "price should be between 0 and 99999")
    require(description.nonEmpty && description.length < 500,
      "description of product is mandatory which should be less than 500 characters")
    require(image.nonEmpty && image.length < 1000, "image URL is mandatory")
    require(category.nonEmpty && category.length < 1000, "category is mandatory")
  }

  case class ExceptionMessage(errorDetails: String)
}

object JsonProtocol extends DefaultJsonProtocol {
  implicit var productFormat = jsonFormat5(Product)
}
