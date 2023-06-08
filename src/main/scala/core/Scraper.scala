package core

import core.{Writer, PathBuilder, ParsedElement}

trait Scraper(protected val writer: Writer) {
  def scrape(url: String): List[ParsedElement]
}
