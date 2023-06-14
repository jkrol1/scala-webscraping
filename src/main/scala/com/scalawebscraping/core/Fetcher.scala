package com.scalawebscraping.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait Fetcher {
  def fetch(url: String): Document
}

object JsoupFetcher extends Fetcher {
  override def fetch(url: String): Document = {
    Jsoup.connect(url).get()
  }
}