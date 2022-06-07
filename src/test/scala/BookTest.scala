import deere.Book
import org.scalatest.funsuite.AnyFunSuite

class BookTest extends AnyFunSuite {

  test("apply should take the word string and return an array split by single space") {
    val b: Book = Book("Hello World")
    assert(b.words === Array("Hello", "World"))
  }

  test("apply should take the word string and return an array split by tab") {
    val b: Book = Book("Hello World.  How are you")
    assert(b.words === Array("Hello", "World.", "How", "are", "you"))
  }

  test("topNWords removes special characters from word") {
    val b = Book("g%^ b c d g*$ b")
    val expected = Array("g", "b")

    assert(b.topNWords(2) === expected)
  }

  test("topNWords is case insensitive") {
    val b = Book("red ReD RED REd rED reD")
    val expected = Array("red")

    assert(b.topNWords(6) === expected)
  }

  test("topNWords returns only the top 50 occurring words") {
    val b = Book("g b c d g b")
    val expected = Array("g", "b")

    assert(b.topNWords(2) === expected)
  }
}
