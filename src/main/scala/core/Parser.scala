package core

import org.jsoup.nodes.Document

trait Parser {
  def parse(doc: Document): List[ParsedElement]
}
