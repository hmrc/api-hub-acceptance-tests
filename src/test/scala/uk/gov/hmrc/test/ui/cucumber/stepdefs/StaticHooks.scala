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
import io.restassured.RestAssured.`given`
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver

import java.io.File
import java.nio.file.{Files, Paths}

object StaticHooks extends ScalaDsl with EN with BrowserDriver {
  BeforeAll {
    val files = Files.list(Paths.get(System.getProperty("user.dir") + "/src/test/resources/publish-files"))
    files.forEach(file => sendFileToPublish(file.toString))
  }

  private def sendFileToPublish(filename: String): Unit = {
    val result = given()
      .baseUri(TestConfiguration.url("oas-files"))
      .multiPart(
        "selectedFile",
        new File(filename),
        "multipart/form-data"
      )
      .header("Authorization", "dGVzdC1hdXRoLWtleQ==")
      .accept("application/json")
      .contentType("multipart/form-data")
      .header("x-platform-type", "HIP")
      .header("x-specification-type", "OAS_V3")
      .when()
      .put("/apis/multipart/publish")

    if (result.statusCode() == 201 || result.statusCode() == 200) {
      println("OAS file successfully published, response code is: " + result.statusCode())
    } else {
      println("There was a problem publishing the OAS file, response code is: " + result.statusCode())
    }
  }
}
