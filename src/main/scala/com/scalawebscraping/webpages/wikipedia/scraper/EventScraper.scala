package com.scalawebscraping.webpages.wikipedia.scraper

import com.scalawebscraping.webpages.wikipedia.parsedelements.Event
import com.scalawebscraping.webpages.wikipedia.parser.EventParser
import com.scalawebscraping.core.{LocalHTMLWriter, LocalPathBuilder, ScraperBase}

class EventScraper extends ScraperBase[Event](LocalHTMLWriter(LocalPathBuilder), EventParser)