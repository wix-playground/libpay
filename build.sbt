name := "libpay-all"
version := "1.6.0-SNAPSHOT"
organization := "com.wix.pay"
licenses := Seq("Apache License, ASL Version 2.0" → url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.11.11"
crossScalaVersions := Seq("2.11.11", "2.12.2")
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Xlint:-missing-interpolator"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val publishSettings = Seq(
  publishMavenStyle := true
  , publishArtifact in Test := false
  , pomIncludeRepository := (_ ⇒ false)
  , publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }
  , pomExtra :=
    <url>https://github.com/wix/libpay</url>
      <scm>
        <url>git@github.com:wix/libpay.git</url>
        <connection>scm:git:git@github.com:wix/libpay.git</connection>
      </scm>
      <developers>
        <developer>
          <id>ohadraz</id>
          <name>Ohad Raz</name>
        </developer>
      </developers>
)

lazy val api = Project(
  id = "libpay-api"
  , base = file("libpay-api")
  , settings = publishSettings ++ Seq(libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.wix.pay" %% "credit-card" % "1.8.0-SNAPSHOT")
  )
)

lazy val root = Project(
  id = "root"
  , base = file(".")
).aggregate(api, testkit)


lazy val testkit = Project(
  id = "libpay-testkit",
  base = file("libpay-testkit")
).dependsOn(api)