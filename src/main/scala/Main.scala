import core.LocalHTMLWriter

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import concurrent.ExecutionContext.Implicits.global
import wikipedia.URL
import wikipedia.scraper.{EventScraper, EventsScraper}

import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]): Unit = {
    val t1 = System.nanoTime
    val eventsScraper = EventsScraper(LocalHTMLWriter)
    val events = eventsScraper.scrape()
    val eventsUrls = events.map(eventLink => s"${URL.BASE.string}${eventLink.href}").slice(0, 50)
    val eventScraper = EventScraper(LocalHTMLWriter)
    val futureList = for {
      url <- eventsUrls
    } yield Future {
      eventScraper.scrape(url)
    }

    val awaitedFutures = waitAll(futureList)
    Await.result(awaitedFutures, Duration.Inf)

    println((System.nanoTime - t1) / 1e9d)
  }

  private def lift[T](futures: Seq[Future[T]]) =
    futures.map(_.map {
      Success(_)
    }.recover { case t => Failure(t) })

  private def waitAll[T](futures: Seq[Future[T]]) =
    Future.sequence(lift(futures))

}