package com.scalawebscraping.webpages.wikipedia.parser

import com.scalawebscraping.webpages.wikipedia.parsedelements.EventLink

import java.net.URLDecoder
import com.scalawebscraping.core.Parser
import org.jsoup.nodes.Document

import scala.jdk.CollectionConverters.*

object EventLinkTableParser extends Parser[EventLink] {
  override def parse(doc: Document): List[EventLink] = {
    val eventsLinksElements = doc.select("table#Scheduled_events tr td:nth-child(1) a[title]").asScala.toList ++
      doc.select("table#Past_events td:nth-child(2) a").asScala.toList
    for {
      link <- eventsLinksElements
    } yield EventLink(URLDecoder.decode(link.attr("href"), "UTF-8"), link.text)
  }
}