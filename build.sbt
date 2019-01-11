name := "SimpleClientRest"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= List(
  "com.softwaremill.sttp" %% "akka-http-backend" % "1.5.3",
  "com.softwaremill.sttp" %% "json4s" % "1.5.3",
  "org.json4s" %% "json4s-native" % "3.6.0",
  "com.softwaremill.sttp" %% "core" % "1.5.3"
)
