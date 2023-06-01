package scraper

import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try}
import scala.util.matching.Regex

import org.jsoup.*
import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements


abstract class ParsedElement

case class EventLink(href: String, title: String) extends ParsedElement

trait Parser {
  def parse(doc: Document): List[ParsedElement]
}

object EventLinkTableParser extends Parser {
  override def parse(doc: Document): List[ParsedElement] = {
    val eventsLinksElements = doc.select("table#Scheduled_events td:nth-child(2) a").asScala.toList ++
      doc.select("table#Past_events td:nth-child(2) a").asScala.toList
    for {
      link <- eventsLinksElements
    } yield EventLink(link.attr("href"), link.text)
  }
}

object Scraper {
  def main(args: Array[String]): Unit = {
    val doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_UFC_events").get()
    println(EventLinkTableParser.parse(doc))
  }
}