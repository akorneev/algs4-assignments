name := "algs4-assignments"

version := "1.0"

lazy val root = Project("algs4-assignments", file("."))
  .aggregate(assignment1)

lazy val assignment1 = project in file("assignment1")