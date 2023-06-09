package com.scalawebscraping.core

import com.scalawebscraping.core.{ParsedElement, PathBuilder, Writer}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait Scraper {
  def scrape(url: String): List[ParsedElement]
}

class ScraperBase[+T <: ParsedElement](protected val writer: Writer, protected val parser: Parser[T]) extends Scraper {
  def scrape(url: String): List[T] = {
    val doc = fetch(url)
    saveHTML(doc)
    parse(doc)
  }

  private def saveHTML(doc: Document): Unit =
    writer.write(doc.outerHtml(), this.getClass.getSimpleName)

  private def fetch(url: String): Document =
    Jsoup.connect(url).get()

  private def parse(doc: Document): List[T] =
    parser.parse(doc)
}
