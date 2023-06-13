package com.scalawebscraping.utilities

import java.nio.file.{Files, Path}

object DeleteDirectory {
  def apply(path: String): Unit = {
    val directoryPath: Path = Path.of(path)
    deleteDirectory(directoryPath)
  }

  private def deleteDirectory(path: Path): Unit = {
    if (Files.exists(path)) {
      Files.walk(path)
        .sorted(java.util.Comparator.reverseOrder())
        .forEach(p => Files.deleteIfExists(p))
    }
  }
}
