package com.scalawebscraping.webpages.wikipedia

import com.scalawebscraping.webpages.wikipedia.parsedelements.{Event, EventLink}
import com.scalawebscraping.webpages.wikipedia.parser.{EventLinkTableParser, EventParser}
import com.scalawebscraping.webpages.wikipedia.scraper.{EventLinkScraper, EventScraper}
import com.scalawebscraping.core.LocalHTMLWriter

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration.Duration
import concurrent.ExecutionContext.Implicits.global
import com.scalawebscraping.core.ScraperBase

object WikipediaScrapingController {
  def apply(): Unit = {
    val eventsScraper = new EventLinkScraper()
    val events = eventsScraper.scrape(URL.EVENTS.string)
    val eventsUrls = events.map(eventLink => s"${URL.BASE.string}${eventLink.href}").slice(0, 2)
    val eventScraper = new EventScraper()
    val futureList = for {
      url <- eventsUrls
    } yield Future {
      eventScraper.scrape(url)
    }

    val awaitedFutures = waitAll(futureList)
    Await.result(awaitedFutures, Duration.Inf)
  }

  private def lift[T](futures: Seq[Future[T]]) =
    futures.map(_.map {
      Success(_)
    }.recover { case t => Failure(t) })

  private def waitAll[T](futures: Seq[Future[T]]) =
    Future.sequence(lift(futures))
}
