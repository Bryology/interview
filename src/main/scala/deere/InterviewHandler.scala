package deere

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class InterviewHandler extends RequestHandler[Any, String] {

  @Override
  def handleRequest(event: Any, context: Context): String = {
    val book: Book = BookService.retrieveBook("https://www.gutenberg.org/files/2701/2701-0.txt")
    val topWords = book.topNWords(50)
    topWords.mkString("(", ", ", ")")
  }
}

object Interview {
  def main(args: Array[String]): Unit = {
    val book: Book = BookService.retrieveBook("https://www.gutenberg.org/files/2701/2701-0.txt")
    val topWords = book.topNWords(50)
    print(topWords.mkString("(", ", ", ")"))
  }
}
