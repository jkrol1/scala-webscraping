package com.scalawebscraping.core

import org.jsoup.nodes.Document

trait Parser[+T <: ParsedElement] {
  def parse(doc: Document): List[T]
}
