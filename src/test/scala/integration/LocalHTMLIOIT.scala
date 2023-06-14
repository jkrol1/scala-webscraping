package integration

import com.scalawebscraping.core.{LocalHTMLReader, LocalHTMLWriter, PathBuilder}
import com.scalawebscraping.utilities.DeleteDirectory
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.File
import scala.reflect.Selectable.reflectiveSelectable

trait PathBuilderFixture extends SuiteMixin {
  this: Suite =>

  val pathBuilder: PathBuilder = new PathBuilder {
    override def buildForScraped(scraperName: String, fileExtension: String): String = s"test/$scraperName.html"

    override def buildForParsedElements(scraperName: String, fileExtension: String): String = "test_parsed/event_link_table_parser.html"
  }
}


class LocalHTMLIOIT extends AnyFlatSpec with BeforeAndAfterEach with PathBuilderFixture {

  def fixture: Object {
    val htmlString: String
  } =
    new {
      val htmlString: String =
        """
          |<!DOCTYPE html>
          |<html>
          |  <head>
          |    <title>Empty Webpage</title>
          |  </head>
          |  <body>
          |    <h1>This is an Empty Webpage</h1>
          |    <p>No content to display.</p>
          |  </body>
          |</html>
          |""".stripMargin
    }

  "A LocalHTMLWriter and LocalHTMLReader" should "Write and read files containing scraped HTML" in {
    val writer = new LocalHTMLWriter(pathBuilder)
    writer.write(fixture.htmlString, "testScraper")
    val readHtml = LocalHTMLReader.read("test/testScraper.html")

    assert(readHtml.title == "Empty Webpage")
    assert(readHtml.select("h1").text() == "This is an Empty Webpage")
    assert(readHtml.select("p").text() == "No content to display.")
  }

  override def afterEach(): Unit = {
    DeleteDirectory("test/")
  }
}
