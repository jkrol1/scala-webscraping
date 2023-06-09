package com.scalawebscraping.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.scalawebscraping.core.{Writer, PathBuilder, ParsedElement}

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
