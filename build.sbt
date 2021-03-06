name := "Basic SBT ScalaTest Test"

version := "1.1"


scalaVersion := "2.10.2"

resolvers ++=
Seq("repo" at "http://repo.typesafe.com/typesafe/releases/",
"Spray Repository"    at "http://repo.spray.io",
"Eligosource Snapshots" at "http://repo.eligotech.com/nexus/content/repositories/eligosource-snapshots",
"Spray Nightlies"     at "http://nightlies.spray.io/")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "io.spray" %%  "spray-json" % "1.2.5",
  "io.spray" % "spray-can" % "1.1-M8",
  "io.spray" % "spray-routing" % "1.1-M8",
  "io.spray" % "spray-http" % "1.1-M8",
  "io.spray" % "spray-servlet" % "1.1-M8",
  "io.spray" % "spray-testkit" % "1.1-M8",
  "io.spray" % "spray-util" % "1.1-M8",
  "io.spray" % "spray-client" % "1.1-M8",
  "org.mockito" % "mockito-all" % "1.8.4",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "com.typesafe.akka" %% "akka-camel" % "2.2.1",
  "com.typesafe.akka" %% "akka-remote"   % "2.2.1",
  "com.typesafe.akka" %% "akka-agent"    % "2.2.1",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.1",
  "mysql" % "mysql-connector-java" % "5.1.24",
  "org.elasticsearch" % "elasticsearch" % "0.90.3",
  "org.apache.activemq" % "activemq-core" % "5.7.0",
  "org.apache.camel" % "camel-scala" % "2.11.1",
  "org.apache.camel" % "camel-core" % "2.11.1",
  "org.apache.camel" % "camel-jms" % "2.11.1",
  "org.eligosource" %% "eventsourced-journal-leveldb" % "0.7-SNAPSHOT",
  "org.eligosource" %% "eventsourced-core" % "0.7-SNAPSHOT",
  "org.clapper" %% "grizzled-slf4j" % "1.0.1"
)




