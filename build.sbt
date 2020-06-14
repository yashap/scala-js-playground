enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

name := "scala-js-playground"
version := "0.1"
scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.0.0",
  "com.lihaoyi" %%% "scalatags" % "0.9.1",
  "org.scalatest" %%% "scalatest" % "3.1.2" % Test
)

npmDependencies in Test ++= Seq(
  "canvas" -> "2.6.1"
)

requireJsDomEnv in Test := true
useYarn := true
scalaJSUseMainModuleInitializer := true
