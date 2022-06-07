package deere

import scala.collection.parallel.CollectionConverters._

case class Book(words: List[String]) {
  private final val common = Array("the", "of", "to", "and", "a", "in", "is", "it", "you", "that", "he", "was", "for", "on", "are", "with", "as", "i", "his", "they", "be", "at", "one", "have", "this", "from", "or", "had", "by", "not", "word", "but", "what", "some", "we", "can", "out", "other", "were", "all", "there", "when", "up", "use", "your", "how", "said", "an", "each", "she")

  def topNWords(n: Int): List[String] =
    words
      .par
      .flatMap(sanitize)
      .foldLeft(Map[String, Int]().withDefaultValue(0))((wordsMap, word) => wordsMap + (word -> (wordsMap(word) + 1)))
      .filterNot(wo => common.contains(wo._1))
      .toList
      .sortBy(_._2)(Ordering[Int].reverse)
      .take(n)
      .map(_._1)

  //This is much faster than the method above, but the question asks us to use the collections api, which this doesn't really.
  def topNWordsPriorityQueue(n: Int): List[String] = {
    val queue = collection.mutable.PriorityQueue[(String, Int)]()(Ordering.by[(String, Int), Int](_._2))
    words
      .groupBy(identity)
      .view
      .mapValues(_.length)
      .toSet
      .foreach { word: (String, Int) =>
        val sanitized = sanitize(word._1)
        if (sanitized.nonEmpty && !common.contains(sanitized.get)) queue.enqueue((sanitized.get, word._2))
      }
    queue.dequeueAll.take(n).map(_._1).toList
  }

  private def sanitize(word: String): Option[String] = """\b\p{L}+(?:-\p{L}+)*\b""".r.findAllIn(word.toLowerCase).toList.headOption
}

object Book {
  def apply(words: String): Book = Book(words.split("\\s+").toList)
}