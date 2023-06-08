package wikipedia.scraper

import core.{Scraper, Writer}
import wikipedia.parsedelements.Event
import wikipedia.parser.EventParser
import org.jsoup.Jsoup

class EventScraper(override protected val writer: Writer) extends Scraper(writer) {
  override def scrape(url: String): List[Event] = {
    val doc = Jsoup.connect(url).get()
    writer.write(doc.outerHtml(), this.getClass.getSimpleName)
    EventParser.parse(doc)
  }
}
