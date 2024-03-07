import sbt._

object Dependencies {

  val test = Seq(
    "ch.qos.logback"        % "logback-classic"        % "1.5.1"    % Test,
    "com.vladsch.flexmark"  % "flexmark-all"           % "0.64.8"   % Test,
    "org.scalatest"        %% "scalatest"              % "3.2.18"   % Test,
    "uk.gov.hmrc"          %% "ui-test-runner"         % "0.21.0"   % Test,
    "io.cucumber"          %% "cucumber-scala"         % "8.20.0"   % Test,
    "io.cucumber"           % "cucumber-junit"         % "7.15.0"   % Test,
    "junit"                 % "junit"                  % "4.13.2"   % Test,
    "com.novocode"          % "junit-interface"        % "0.11"     % Test,
    "ch.qos.logback"        % "logback-classic"        % "1.5.1"    % Test,
    "com.vladsch.flexmark"  % "flexmark-all"           % "0.64.8"   % Test,
    "org.scalatest"        %% "scalatest"              % "3.2.18"   % Test,
    "com.typesafe"          % "config"                 % "1.4.2"    % Test,
    "org.scalatestplus"    %% "selenium-4-2"           % "3.2.13.0" % Test,
    "junit"                 % "junit"                  % "4.13.2"   % Test,
    "uk.gov.hmrc"          %% "ui-test-runner"         % "0.21.0"   % Test,
    "io.github.etspaceman" %% "scalacheck-faker"       % "7.0.0"    % Test,
    "io.cucumber"           % "cucumber-picocontainer" % "7.11.0"   % Test,
    "io.rest-assured"       % "scala-support"          % "5.3.2"    % Test
  )
}
