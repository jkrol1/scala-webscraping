package com.scalawebscraping.core

import org.jsoup.nodes.Document

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.util.Try

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait Reader {
  def read(filePath: String): Document
}

object LocalHTMLReader extends Reader {
  def read(filePath: String): Document = {
    val bufferedSource = scala.io.Source.fromFile(filePath)
    val htmlText = bufferedSource.getLines().mkString
    Jsoup.parse(htmlText)
  }
}