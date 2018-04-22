name := "StreamingKafka"
version := "0.1"
scalaVersion := "2.11.8"

mainClass in (Compile, run) := Some("scala.PrintTweets")
mainClass in (Compile, packageBin) := Some("scala.PrintTweets")

val sparkVersion = "1.6.1"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.1" ,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion,
  "org.scalatest" %% "scalatest" % "2.2.2" % Test
)
