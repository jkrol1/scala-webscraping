package wikipedia.parser

import core.{ParsedElement, Parser}
import org.jsoup.nodes.Document
import wikipedia.parsedelements.EventLink

import scala.jdk.CollectionConverters.*

object EventLinkTableParser extends Parser {
  override def parse(doc: Document): List[ParsedElement] = {
    val eventsLinksElements = doc.select("table#Scheduled_events td:nth-child(2) a").asScala.toList ++
      doc.select("table#Past_events td:nth-child(2) a").asScala.toList
    for {
      link <- eventsLinksElements
    } yield EventLink(link.attr("href"), link.text)
  }
}