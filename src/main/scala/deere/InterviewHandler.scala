package deere

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class InterviewHandler extends RequestHandler[Any, String] {

  @Override
  def handleRequest(event: Any, context: Context): String = {
    BookService
      .retrieveBook("https://www.gutenberg.org/files/2701/2701-0.txt")
      .topNWords(50)
      .mkString("(", ", ", ")")
  }
}

object Interview {
  def main(args: Array[String]): Unit = {
    val topWords = BookService
      .retrieveBook("https://www.gutenberg.org/files/2701/2701-0.txt")
      .topNWords(50)
      .mkString("(", ", ", ")")
    print(topWords)
  }
}
