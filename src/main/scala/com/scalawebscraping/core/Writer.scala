package com.scalawebscraping.core

import com.scalawebscraping.core.{LocalPathBuilder, PathBuilder}

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.util.Try

trait Writer(pathBuilder: PathBuilder) {
  def write(HTMLString: String, scraperName: String): Unit
}

class LocalHTMLWriter(pathBuilder: PathBuilder) extends Writer(pathBuilder) {
  def write(HTMLString: String, scraperName: String): Unit = Try {
    val builtPath = pathBuilder.buildForScraped(scraperName, "html")
    Files.createDirectories(Paths.get(builtPath).getParent)
    val pw = new PrintWriter(new File(builtPath))
    pw.write(HTMLString)
    pw.close()
  }
}
