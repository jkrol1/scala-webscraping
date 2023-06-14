package unit

import scala.util.matching.Regex

import org.scalatest.flatspec.AnyFlatSpec

import com.scalawebscraping.core.LocalPathBuilder

class LocalPathBuilderSpec extends AnyFlatSpec {
  "A LocalPathBuilder" should "build local environment path for raw scraped data, specified scraper name and file extension" in {
    val regex: Regex = """scraped_raw\/ScraperTest_\d+\.json""".r
    val builtPath = LocalPathBuilder.buildForScraped("ScraperTest", "json")

    assert(regex.matches(builtPath))
  }

  it should "build local environment path for parsed html, specified scraper name and file extension" in {
    val regex: Regex = """scraped_parsed\/ScraperTest_\d+\.json""".r
    val builtPath = LocalPathBuilder.buildForParsedElements("ScraperTest", "json")

    assert(regex.matches(builtPath))
  }
}
