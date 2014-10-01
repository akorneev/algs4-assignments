name := "algs4-assignments"

version := "1.0"

lazy val root = Project("algs4-assignments", file("."))
  .aggregate(assignment1, assignment2)

lazy val assignment1 = project

lazy val assignment2 = project
