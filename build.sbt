enablePlugins(ScalaJSPlugin)

name := "scala-js-playground"
version := "0.1"
scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.0.0",
  "com.lihaoyi" %%% "scalatags" % "0.9.1",
  "org.scalatest" %%% "scalatest" % "3.1.2" % Test
)

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

scalaJSUseMainModuleInitializer := true
