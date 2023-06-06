import core.LocalHTMLWriter
import wikipedia.scraper.EventsScraper

object Main {
  def main(args: Array[String]): Unit = {
    val eventsScraper = EventsScraper(LocalHTMLWriter)
    eventsScraper.scrape()
  }
}