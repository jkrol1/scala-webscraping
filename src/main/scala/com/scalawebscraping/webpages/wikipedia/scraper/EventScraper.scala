package com.scalawebscraping.webpages.wikipedia.scraper

import com.scalawebscraping.core.{JsoupFetcher, LocalHTMLWriter, LocalPathBuilder, ScraperBase}
import com.scalawebscraping.webpages.wikipedia.parsedelements.Event
import com.scalawebscraping.webpages.wikipedia.parser.EventParser

class EventScraper extends ScraperBase[Event](JsoupFetcher, LocalHTMLWriter(LocalPathBuilder), EventParser)