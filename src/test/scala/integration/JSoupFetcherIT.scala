package integration

import com.scalawebscraping.core.JsoupFetcher
import com.scalawebscraping.webpages.wikipedia.URL
import org.jsoup.nodes.Document
import org.scalatest.flatspec.AnyFlatSpec

class JSoupFetcherIT extends AnyFlatSpec {
  "JSoupFetcher" should "fetch HTML from given URL and return Document object" in {
    val document: Document = JsoupFetcher.fetch(URL.EVENTS.string)

    assert(document.location() == "https://en.wikipedia.org/wiki/List_of_UFC_events")
    assert(document.title() == "List of UFC events - Wikipedia")
    assert(document.childNodes().size() >= 0)
  }
}
