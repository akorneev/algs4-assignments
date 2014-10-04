name := "assignment2"

version := "1.0"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

mainClass in(Compile, run) := Some("Subset")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.6" % "test"
)
