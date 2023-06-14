package unit

import com.scalawebscraping.core.LocalHTMLReader
import com.scalawebscraping.webpages.wikipedia.parser.EventLinkTableParser
import org.jsoup.nodes.Document
import org.scalatest.flatspec.AnyFlatSpec

import scala.reflect.Selectable.reflectiveSelectable

class EventLinkTableParserSpec extends AnyFlatSpec {
  "EventLinkTableParser" should "parse wiki/List_of_UFC_events page and return list of event links" in {
    val document = LocalHTMLReader.read("src/test/scala/data/event_link_table_parser.html")
    val eventLinks = EventLinkTableParser.parse(LocalHTMLReader.read("src/test/scala/data/event_link_table_parser.html"))

    assert(eventLinks.size == 6)
    assert(eventLinks.head.href == "/wiki/UFC_294" && eventLinks.head.title == "UFC 294")
    assert(eventLinks.last.href == "/wiki/UFC_288" && eventLinks.last.title == "UFC 288: Sterling vs. Cejudo")
  }
}
