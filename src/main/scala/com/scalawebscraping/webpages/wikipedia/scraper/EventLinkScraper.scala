package com.scalawebscraping.webpages.wikipedia.scraper

import com.scalawebscraping.webpages.wikipedia.parsedelements.EventLink
import com.scalawebscraping.webpages.wikipedia.parser.EventLinkTableParser
import com.scalawebscraping.core.{JsoupFetcher, LocalHTMLWriter, LocalPathBuilder, ScraperBase}

class EventLinkScraper extends ScraperBase[EventLink](JsoupFetcher, LocalHTMLWriter(LocalPathBuilder), EventLinkTableParser)