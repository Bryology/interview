import sbt.Keys._
import sbt._

ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "deere",
    scalaVersion := "2.13.8",
    resolvers += Resolver.sonatypeRepo("public"),
    assembly / assemblyJarName  := "deere-interview.jar",
    libraryDependencies ++= Seq(
      "org.scalaj" %% "scalaj-http" % "2.4.2",
      "org.scalactic" %% "scalactic" % "3.2.12",
      "org.scalatest" %% "scalatest" % "3.2.12" % "test",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
      "com.amazonaws" % "aws-lambda-java-events" % "3.11.0",
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.1"
    )
  )
