package com.scalawebscraping.webpages.wikipedia.scraper

import com.scalawebscraping.webpages.wikipedia.parsedelements.EventLink
import com.scalawebscraping.webpages.wikipedia.parser.EventLinkTableParser
import com.scalawebscraping.core.{LocalHTMLWriter, ScraperBase}

class EventLinkScraper extends ScraperBase[EventLink](LocalHTMLWriter, EventLinkTableParser)