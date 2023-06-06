package core

import java.io.{File, PrintWriter}
import core.{LocalPathBuilder, PathBuilder}

import java.nio.file.{Files, Paths}
import scala.util.Try

trait Writer(protected val pathBuilder: PathBuilder) {
  def write(HTMLString: String, scraperName: String): Unit
}

object LocalHTMLWriter extends Writer(LocalPathBuilder) {
  def write(HTMLString: String, scraperName: String): Unit = Try {
    val builtPath = pathBuilder.build(scraperName, "html")
    println(Paths.get(builtPath).getParent)
    Files.createDirectories(Paths.get(builtPath).getParent)
    val pw = new PrintWriter(new File(builtPath))
    pw.write(HTMLString)
    pw.close()
  }
}
