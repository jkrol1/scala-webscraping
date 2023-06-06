package core

import core.{Writer, PathBuilder}

trait Scraper(protected val writer: Writer) {
  def scrape(url: String): Unit
}
