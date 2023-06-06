package wikipedia.scraper

import core.{LocalHTMLWriter, Scraper, Writer}
import wikipedia.parser.EventLinkTableParser
//import wikipedia.scraper.EventScraper
import wikipedia.URL
import org.jsoup.Jsoup

class EventsScraper(override protected val writer: Writer) extends Scraper(writer) {
  override def scrape(url: String = URL.EVENTS.string): Unit = {
    val doc = Jsoup.connect(url).get()
    writer.write(doc.outerHtml(), this.getClass.getSimpleName)
    val eventLinkElements = EventLinkTableParser.parse(doc)
    val eventUrls = eventLinkElements.map(eventLink => s"${URL.BASE.string}${eventLink.href}")
  }
}
