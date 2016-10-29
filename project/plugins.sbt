resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")
addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.4")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")
addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.1")