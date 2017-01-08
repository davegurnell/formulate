name              in ThisBuild := "formulate"
organization      in ThisBuild := "com.davegurnell"
version           in ThisBuild := "0.1.0"

scalaOrganization in ThisBuild := "org.typelevel"
scalaVersion      in ThisBuild := "2.11.8"

enablePlugins(ScalaJSPlugin)

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xfatal-warnings"
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.typelevel"                     %%% "cats-core"       % "0.8.1",
  "com.github.julien-truffaut"        %%% "monocle-core"    % "1.4.0-M1",
  "com.github.julien-truffaut"        %%% "monocle-generic" % "1.4.0-M1",
  "com.github.julien-truffaut"        %%% "monocle-macro"   % "1.4.0-M1",

  "org.scala-js"                      %%% "scalajs-dom"     % "0.9.1",

  "com.github.japgolly.scalajs-react" %%% "core"            % "0.11.3",
  "com.github.japgolly.scalajs-react" %%% "extra"           % "0.11.3",
  "com.github.japgolly.scalajs-react" %%% "ext-monocle"     % "0.11.3",
  "com.github.japgolly.scalacss"      %%% "ext-react"       % "0.5.1",

  "com.chuusai"                       %%% "shapeless"       % "2.3.2",

  "org.scalatest"                     %%% "scalatest"       % "3.0.0" % Test
)

jsDependencies ++= Seq(
  "org.webjars.bower" % "react" % "15.3.2" / "react-with-addons.js" minified "react-with-addons.min.js"                                  commonJSName "React",
  "org.webjars.bower" % "react" % "15.3.2" / "react-dom.js"         minified "react-dom.min.js"         dependsOn "react-with-addons.js" commonJSName "ReactDOM",
  "org.webjars.bower" % "react" % "15.3.2" / "react-dom-server.js"  minified "react-dom-server.min.js"  dependsOn "react-dom.js"         commonJSName "ReactDOMServer"
)
