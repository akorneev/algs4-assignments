name := "assignment2"

version := "1.0"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

mainClass in(Compile, run) := Some("Subset")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)
