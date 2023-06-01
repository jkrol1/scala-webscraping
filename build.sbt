ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.0.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-webscraping",
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % "1.15.4"
    )
  )
