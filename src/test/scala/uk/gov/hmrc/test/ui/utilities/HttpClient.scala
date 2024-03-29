/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.utilities

import io.restassured.RestAssured.`given`
import io.restassured.response.Response
import uk.gov.hmrc.test.ui.conf.TestConfiguration

import java.io.File

object HttpClient {
  def publishFile(filename: String): Response =
    given()
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
}
