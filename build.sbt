// *****************************************************************************
// Projects
// *****************************************************************************

lazy val blog =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin, GitVersioning)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.akkaActor,
        library.akkaTyped,
        library.akkaTestkit      % Test,
        library.akkaTypedTestkit % Test,
        library.scalaTest        % Test
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val akka       = "2.5.6"
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.4"
    }
    val akkaActor        = "com.typesafe.akka" %% "akka-actor"         % Version.akka
    val akkaTestkit      = "com.typesafe.akka" %% "akka-testkit"       % Version.akka
    val akkaTyped        = "com.typesafe.akka" %% "akka-typed"         % Version.akka
    val akkaTypedTestkit = "com.typesafe.akka" %% "akka-typed-testkit" % Version.akka
    val scalaTest        = "org.scalatest"     %% "scalatest"          % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  gitSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.3",
    organization := "de.heikoseeberger",
    organizationName := "Heiko Seeberger",
    startYear := Some(2017),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value)
)

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtOnCompile.in(Sbt) := false,
    scalafmtVersion := "1.3.0"
  )
