package scraper

import org.jsoup.*
import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements

import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try}

abstract class ParsedElement

case class ScheduledEvent(name: String, date: String, venue: String, location: String) extends ParsedElement

case class PastEvent(eventNumber: String, name: String, date: String, venue: String, location: String, attendance: Int) extends ParsedElement

trait Parser {
  def parse(doc: Document): List[ParsedElement]
}

trait TableParser extends Parser {
  def parse(doc: Document): List[ParsedElement] = {
    val rawTableRows = parseTableElement(doc.select(selector))
    for {
      row <- rawTableRows
    } yield convertRawRowToParsedElement(row)
  }

  protected def selector: String

  protected def convertRawRowToParsedElement(row: List[String]): ParsedElement

  private def parseTableElement(elements: Elements): List[List[String]] = {
    val rowsWithoutHeader = elements.asScala.filter(
      !_.select("th").hasText
    ).toList
    for {
      row <- rowsWithoutHeader
    } yield row.children.asScala.map {
      _.text
    }.toList
  }


}

object ScheduledEventsParser extends TableParser {
  override protected def selector: String = "table#Scheduled_events tbody tr"

  override protected def convertRawRowToParsedElement(row: List[String]): ParsedElement =
    ScheduledEvent(row.head, row(1), row(2), row(3))
}

object PastEventsParser extends TableParser {
  override protected def selector: String = "table#Past_events tbody tr"

  override protected def convertRawRowToParsedElement(row: List[String]): ParsedElement = {
    PastEvent(getValueFromRowOrUnknown(row.head),
      row(1),
      row(2),
      getValueFromRowOrUnknown(row(3)),
      getValueFromRowOrUnknown(row(4)),
      getAttendance(row)
    )
  }

  private def getAttendance(row: List[String]): Int = {
    Try {
      row(5).toInt
    } match {
      case Success(v) => v
      case Failure(_) => -1
    }
  }

  private def getValueFromRowOrUnknown(row: => String): String = {
    Try {
      row
    } match {
      case Success(v) => v
      case Failure(_) => "-"
    }
  }
}

object Scraper {
  def main(args: Array[String]): Unit = {
    val doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_UFC_events").get()
    println(ScheduledEventsParser.parse(doc).length)
    println(PastEventsParser.parse(doc))
  }

}