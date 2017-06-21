name := "libpay-all"
version in ThisBuild := "1.6.0-SNAPSHOT"
organization in ThisBuild := "com.wix.pay"
licenses in ThisBuild := Seq("Apache License, ASL Version 2.0" → url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion in ThisBuild := "2.11.11"
crossScalaVersions in ThisBuild := Seq("2.11.11", "2.12.2")
scalacOptions in ThisBuild ++= Seq(
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Xlint:-missing-interpolator"
)

resolvers in ThisBuild ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

publishMavenStyle in ThisBuild := true
publishArtifact in Test := false
pomIncludeRepository in ThisBuild := (_ ⇒ false)
publishTo in ThisBuild := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}

pomExtra in ThisBuild :=
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

lazy val api = Project(
  id = "libpay-api"
  , base = file("libpay-api")
  , settings = Seq(name := "libpay-api") ++ 
      Seq(libraryDependencies += "com.wix.pay" %% "credit-card" % "1.8.0-SNAPSHOT" )
)

lazy val testkit = Project(
  id = "libpay-testkit"
  , base = file("libpay-testkit")
  , settings = Seq(name := "libpay-testkit")
).dependsOn(api)

lazy val noPublish = Seq(publish := {}, publishLocal := {}, publishArtifact := false)

lazy val root = Project(
  id = "root"
  , base = file(".")
  , settings = noPublish
).aggregate(api, testkit)


