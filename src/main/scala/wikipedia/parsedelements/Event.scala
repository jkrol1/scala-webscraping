package wikipedia.parsedelements

import core.ParsedElement

case class Event(name: String,
                 date: String,
                 venue: String,
                 city: String,
                 attendance: String,
                 promotion: String) extends ParsedElement
