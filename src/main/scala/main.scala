package scraper

import org.jsoup.*
import core.*
import wikipedia.parser.EventLinkTableParser


object Scraper {
  def main(args: Array[String]): Unit = {
    val doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_UFC_events").get()
    println(EventLinkTableParser.parse(doc))
  }
}