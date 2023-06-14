package unit

import org.scalatest.flatspec.AnyFlatSpec
import org.jsoup.nodes.Document
import reflect.Selectable.reflectiveSelectable

import com.scalawebscraping.core.LocalHTMLReader
import com.scalawebscraping.webpages.wikipedia.parser.EventParser

class EventParserSpec extends AnyFlatSpec {

  "EventParser" should "parse wiki/$event page and return list of 1 element which contains parsed event data" in {
    val document = LocalHTMLReader.read("src/test/scala/data/event.html")
    val parsedElementList = EventParser.parse(document)
    val event = parsedElementList.head

    assert(parsedElementList.size == 1)
    assert(event.name == "UFC 294")
    assert(event.date == "October 21, 2023 (2023-10-21)")
    assert(event.venue == "Etihad Arena")
    assert(event.city == "Abu Dhabi, United Arab Emirates")
    assert(event.attendance == "")
    assert(event.promotion == "Ultimate Fighting Championship")
  }
}
