/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import io.cucumber.scala.{EN, ScalaDsl}
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import uk.gov.hmrc.test.ui.utilities.HttpClient

import java.nio.file.{Files, Paths}

object StaticHooks extends ScalaDsl with EN with BrowserDriver {
  private val publishFilesPath = "/src/test/resources/publish-files"
  private val successMessage   = "OAS file successfully published, response code is: "
  private val failureMessage   = "There was a problem publishing the OAS file, response code is: "

  BeforeAll {
    val files = Files.list(Paths.get(System.getProperty("user.dir") + publishFilesPath))
    files.forEach(file => sendFileToPublish(file.toString))
  }

  private def sendFileToPublish(filename: String): Unit = {
    val result = HttpClient.publishFile(filename)

    if (result.statusCode() == 201 || result.statusCode() == 200) {
      logger.info(successMessage + result.statusCode())
    } else {
      logger.info(failureMessage + result.statusCode())
    }
  }
}
