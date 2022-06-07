package deere

import scalaj.http.Http

object BookService {
  def retrieveBook(url: String): Book = Book(Http(url).asString.body)
}
