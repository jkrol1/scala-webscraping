import com.scalawebscraping.webpages.wikipedia.WikipediaScrapingController
import com.scalawebscraping.utilities.MeasureRuntime

object Main {
  def main(args: Array[String]): Unit = {
    MeasureRuntime(WikipediaScrapingController())
  }
}