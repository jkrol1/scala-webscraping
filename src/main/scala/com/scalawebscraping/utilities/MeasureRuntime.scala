package com.scalawebscraping.utilities

object MeasureRuntime {
  def apply(runnable: => AnyVal): Unit = {
    val t1 = System.nanoTime
    runnable
    println((System.nanoTime - t1) / 1e9d)
  }
}
