package com.scalawebscraping.webpages.wikipedia.parser

import com.scalawebscraping.core.Parser
import com.scalawebscraping.webpages.wikipedia.parsedelements.Event
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import scala.jdk.CollectionConverters.*

object EventParser extends Parser[Event] {
  override def parse(doc: Document): List[Event] = {
    val title = parseTitle(doc)
    val infobox = parseInfoBox(doc)
    val date = parseInfoboxRowByLabel(infobox, "Date")
    val venue = parseInfoboxRowByLabel(infobox, "Venue")
    val city = parseInfoboxRowByLabel(infobox, "City")
    val attendance = parseInfoboxRowByLabel(infobox, "Attendance")
    val promotion = parseInfoboxRowByLabel(infobox, "Promotion")
    List(Event(name = title, date = date, venue = venue, city = city, attendance = attendance, promotion = promotion))
  }

  private def parseTitle(doc: Document): String =
    doc.select("h1.firstHeading").text

  private def parseInfoBox(doc: Document): Elements =
    doc.select("table.infobox tr")

  private def parseInfoboxRowByLabel(infoBox: Elements, label: String): String =
    infoBox.select(s"th.infobox-label:contains($label) + td.infobox-data").text
}