name := """poc-2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.google.code.morphia" % "morphia" % "0.99",
  "org.mongodb" % "mongo-java-driver" % "2.7.3",
  "com.google.code.morphia" % "morphia-logging-slf4j" % "0.99"
)

resolvers ++= Seq(
	"Maven repository" at "http://morphia.googlecode.com/svn/mavenrepo/",
	"MongoDb Java Driver Repository" at "http://repo1.maven.org/maven2/org/mongodb/mongo-java-driver/"
)
