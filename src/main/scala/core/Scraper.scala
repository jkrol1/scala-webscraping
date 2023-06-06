package core

trait Scraper {
  def scrape(url: String): Unit
}
