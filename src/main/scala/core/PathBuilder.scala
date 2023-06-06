package core

import java.util.Calendar

trait PathBuilder {
  def build(scraperName: String, fileExtension: String): String

  def buildForParsedElements(scraperName: String, fileExtension: String): String
}

object LocalPathBuilder extends PathBuilder {
  override def build(scraperName: String, fileExtension: String): String =
    s"scraped_raw/${_build(scraperName, fileExtension)}"

  override def buildForParsedElements(scraperName: String, fileExtension: String): String =
    s"scraped_parsed/${_build(scraperName, fileExtension)}"

  private def _build(scraperName: String, fileExtension: String): String =
    s"${scraperName}_${Calendar.getInstance().getTime.toString.replace(" ", "_")}.$fileExtension"
}
