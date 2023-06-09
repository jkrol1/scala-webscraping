package com.scalawebscraping.webpages.wikipedia

enum URL(val string: String) {
  case BASE extends URL("https://en.wikipedia.org")
  case EVENTS extends URL("https://en.wikipedia.org/wiki/List_of_UFC_events")
}