import AssemblyKeys._
import NativePackagerHelper._

enablePlugins(JavaAppPackaging, DockerPlugin)

assemblySettings

lazy val commonSettings = Seq(
  name := "neuron",
  version := "0.0.1",
  scalaVersion := "2.11.8",
  parallelExecution in ThisBuild := false,
  test in assembly := {}
)

val akkaVersion = "2.5.14"
val httpVersion = "10.1.3"

lazy val poc = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq (
      "com.typesafe.akka"          %% "akka-actor"           % akkaVersion,
      "com.typesafe.akka"          %% "akka-stream"          % akkaVersion,
      "com.typesafe.akka"          %% "akka-http"            % "10.1.3",
      "com.typesafe.akka"          %% "akka-http-spray-json" % "10.1.3",
      "com.typesafe.slick"         %% "slick"                % "3.2.3",
      "org.flywaydb"               %  "flyway-core"          % "5.0.4",
      "org.postgresql"             %  "postgresql"           % "42.0.0",
      "org.apache.logging.log4j"   %  "log4j-core"           % "2.8",
      "org.apache.logging.log4j"   %  "log4j-api"            % "2.8",
      "com.typesafe.akka"          %% "akka-testkit"         % "2.5.14" % "test",
      "com.typesafe.akka"          %% "akka-http-testkit"    % "10.1.3" % "test",
      "org.scalatest"              %% "scalatest"            % "3.0.5"  % "test",
      "org.scalamock"              %% "scalamock"            % "4.1.0"  % "test"
    )
  )

mergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}
