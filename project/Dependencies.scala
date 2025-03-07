import sbt._

object Dependencies {

  val test = Seq(
    "com.novocode"          % "junit-interface"        % "0.11"     % Test,
    "com.typesafe"          % "config"                 % "1.4.3"    % Test,
    "io.cucumber"           % "cucumber-junit"         % "7.18.0"   % Test,
    "io.cucumber"           % "cucumber-guice"         % "7.18.0"   % Test,
    "io.cucumber"          %% "cucumber-scala"         % "8.23.0"   % Test,
    "io.github.etspaceman" %% "scalacheck-faker"       % "8.0.4"    % Test,
    "io.rest-assured"       % "scala-support"          % "5.4.0"    % Test,
    "junit"                 % "junit"                  % "4.13.2"   % Test,
    "org.scalatest"        %% "scalatest"              % "3.2.18"   % Test,
    "org.scalatestplus"    %% "selenium-4-2"           % "3.2.13.0" % Test,
    "uk.gov.hmrc"          %% "ui-test-runner"         % "0.45.0"   % Test,
    "com.google.inject"     % "guice"                  % "7.0.0"    % Test
  )
}
