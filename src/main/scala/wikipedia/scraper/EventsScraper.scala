package wikipedia.scraper

import core.{LocalHTMLWriter, Scraper, Writer}
import wikipedia.parser.EventLinkTableParser
import wikipedia.parsedelements.EventLink
import wikipedia.URL
import org.jsoup.Jsoup

class EventsScraper(override protected val writer: Writer) extends Scraper(writer) {
  override def scrape(url: String = URL.EVENTS.string): List[EventLink] = {
    val doc = Jsoup.connect(url).get()
    writer.write(doc.outerHtml(), this.getClass.getSimpleName)
    EventLinkTableParser.parse(doc)
  }
}
