package wikipedia.scraper

import core.Scraper
import wikipedia.parser.EventLinkTableParser
import wikipedia.URL
import org.jsoup.Jsoup

object EventsScraper extends Scraper {
  override def scrape(url: String = URL.EVENTS.string): Unit = {
    val doc = Jsoup.connect(url).get()
    val eventLinkElements = EventLinkTableParser.parse(doc)
    val eventUrls = eventLinkElements.map(eventLink=>s"${URL.BASE.string}${eventLink.href}")
    println(eventUrls)
  }
}
