lazy val commonSettings = Seq(
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.11.8",
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  scalacOptions += "-deprecation",
  scalacOptions += "-feature"
)

lazy val coreSettings = Seq(
  libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6",
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  scalacOptions += "-language:postfixOps"
)


lazy val macro = (project in file("macro")).
  settings(commonSettings:_*).
  settings(libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _))

lazy val root = (project in file(".")).
  dependsOn(macro).
  settings(commonSettings:_*).
  settings(coreSettings:_*)
