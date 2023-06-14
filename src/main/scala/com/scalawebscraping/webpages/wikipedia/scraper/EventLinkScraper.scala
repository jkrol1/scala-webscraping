package com.scalawebscraping.webpages.wikipedia.scraper

import com.scalawebscraping.core.{JsoupFetcher, LocalHTMLWriter, LocalPathBuilder, ScraperBase}
import com.scalawebscraping.webpages.wikipedia.parsedelements.EventLink
import com.scalawebscraping.webpages.wikipedia.parser.EventLinkTableParser

class EventLinkScraper extends ScraperBase[EventLink](JsoupFetcher, LocalHTMLWriter(LocalPathBuilder), EventLinkTableParser)