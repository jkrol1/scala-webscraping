import com.scalawebscraping.utilities.MeasureRuntime
import com.scalawebscraping.webpages.wikipedia.WikipediaScrapingController

object Main {
  def main(args: Array[String]): Unit = {
    MeasureRuntime(WikipediaScrapingController())
  }
}