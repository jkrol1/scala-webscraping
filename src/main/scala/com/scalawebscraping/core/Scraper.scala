package com.scalawebscraping.core

import com.scalawebscraping.core.{ParsedElement, PathBuilder, Writer, Fetcher}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait Scraper {
  def scrape(url: String): List[ParsedElement]
}

class ScraperBase[+T <: ParsedElement](protected val fetcher: Fetcher, protected val writer: Writer, protected val parser: Parser[T]) extends Scraper {
  def scrape(url: String): List[T] = {
    val doc = fetch(url)
    saveHTML(doc)
    parse(doc)
  }

  private def saveHTML(doc: Document): Unit =
    writer.write(doc.outerHtml(), this.getClass.getSimpleName)

  private def fetch(url: String): Document =
    fetcher.fetch(url)

  private def parse(doc: Document): List[T] =
    parser.parse(doc)
}
