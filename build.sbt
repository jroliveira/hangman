enablePlugins(JavaAppPackaging)

name := "hangman"
version := "1.0"
scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.4.10"

  Seq(
    // akka core
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    // akka http
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    // spray
    "io.spray" %% "spray-json" % "1.3.2",
    // testing
    "org.specs2" %% "specs2-core" % "3.8.5" % "test",
    "org.specs2" %% "specs2-mock" % "3.8.5" % "test",
    "org.mockito" % "mockito-core" % "1.8.5",
    // mongodb
    "org.mongodb" %% "casbah" % "3.1.1",
    "com.github.salat" %% "salat" % "1.10.0",
    // log
    "org.slf4j" % "slf4j-simple" % "1.6.4"
  )
}

herokuAppName in Compile := "api-hangman"
herokuJdkVersion in Compile := "1.8"
herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/hangman -Dhttp.port=$PORT"
)
herokuIncludePaths in Compile := Seq("src")