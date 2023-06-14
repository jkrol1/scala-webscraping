package unit

import reflect.Selectable.reflectiveSelectable
import org.scalatest.flatspec.AnyFlatSpec
import org.jsoup.nodes.Document
import com.scalawebscraping.core.{Fetcher, ParsedElement, Parser, LocalPathBuilder, ScraperBase, Writer}
import com.scalawebscraping.webpages.wikipedia.parsedelements.EventLink

class ScraperBaseSpec extends AnyFlatSpec {

  def fixture: Object {
    val fetcher: Fetcher
    val parser: Parser[EventLink]
    val writer: Writer
  } = new {
    val fetcher: Fetcher = (url: String) => Document("")
    val parser: Parser[EventLink] = (doc: Document) => List(EventLink("test", "test"))
    val writer: Writer = new Writer(LocalPathBuilder) {
      override def write(HTMLString: String, scraperName: String): Unit = ()
    }
  }

  "ScraperBase" should "fetch data from given url and return list of parsed elements when scrape is invoked" in {
    val scraper = ScraperBase[EventLink](fixture.fetcher, fixture.writer, fixture.parser)
    val result = scraper.scrape("test")

    assert(result.head.href == "test" && result.head.title == "test")
  }
}
